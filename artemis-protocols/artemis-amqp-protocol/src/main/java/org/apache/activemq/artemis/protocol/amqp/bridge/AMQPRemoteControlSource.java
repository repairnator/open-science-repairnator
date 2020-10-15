/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.artemis.protocol.amqp.bridge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.apache.activemq.artemis.api.core.Message;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.core.postoffice.Binding;
import org.apache.activemq.artemis.core.postoffice.Bindings;
import org.apache.activemq.artemis.core.postoffice.impl.LocalQueueBinding;
import org.apache.activemq.artemis.core.server.ActiveMQComponent;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.MessageReference;
import org.apache.activemq.artemis.core.server.Queue;
import org.apache.activemq.artemis.core.server.RoutingContext;
import org.apache.activemq.artemis.core.server.impl.AddressInfo;
import org.apache.activemq.artemis.core.server.impl.RoutingContextImpl;
import org.apache.activemq.artemis.core.server.remotecontrol.RemoteControl;
import org.apache.activemq.artemis.core.transaction.Transaction;
import org.apache.activemq.artemis.protocol.amqp.broker.AMQPMessage;
import org.apache.activemq.artemis.protocol.amqp.broker.AMQPStandardMessage;
import org.apache.activemq.artemis.protocol.amqp.util.NettyWritable;
import org.apache.activemq.artemis.protocol.amqp.util.TLSEncode;
import org.apache.qpid.proton.amqp.Symbol;
import org.apache.qpid.proton.amqp.messaging.AmqpValue;
import org.apache.qpid.proton.amqp.messaging.ApplicationProperties;
import org.apache.qpid.proton.amqp.messaging.Header;
import org.apache.qpid.proton.amqp.messaging.MessageAnnotations;
import org.apache.qpid.proton.amqp.messaging.Properties;
import org.apache.qpid.proton.amqp.messaging.Section;
import org.apache.qpid.proton.codec.EncoderImpl;
import org.apache.qpid.proton.codec.WritableBuffer;
import org.jboss.logging.Logger;

public class AMQPRemoteControlSource implements RemoteControl, ActiveMQComponent {

   private static final Logger logger = Logger.getLogger(AMQPRemoteControlSource.class);

   public static final Symbol EVENT_TYPE = Symbol.getSymbol("m_EVENT_TYPE");
   public static final String ADD_ADDRESS = "addAddress";
   public static final String DELETE_ADDRESS = "deleteAddress";
   public static final String CREATE_QUEUE = "createQueue";
   public static final String DELETE_QUEUE = "deleteQueue";
   public static final String ADDRESS = "address";
   public static final String QUEUE = "queue";
   public static final String EVENT = "event";

   final SimpleString sourceAddress;
   final ActiveMQServer server;

   boolean started;

   @Override
   public void start() throws Exception {
      if (!started) {
         new Exception ("Starting remote control source").printStackTrace();
         server.installRemoteControl(this);
         started = true;
      }
   }

   @Override
   public void stop() throws Exception {
      if (started) {
         started = false;
         server.removeRemoteControl();
      }
   }

   @Override
   public boolean isStarted() {
      return started;
   }

   public AMQPRemoteControlSource(SimpleString sourceAddress, ActiveMQServer server) {
      this.sourceAddress = sourceAddress;
      this.server = server;
   }

   @Override
   public void addAddress(AddressInfo addressInfo) throws Exception {
      Message message = createMessage(sourceAddress.toString(), addressInfo.getName(), null, ADD_ADDRESS, addressInfo.toJSON());
      route(server, message);
   }

   @Override
   public void deleteAddress(AddressInfo addressInfo) throws Exception {
      Message message = createMessage(sourceAddress.toString(), addressInfo.getName(), null, DELETE_ADDRESS, addressInfo.toJSON());
      route(server, message);
   }

   @Override
   public void createQueue(QueueConfiguration queueConfiguration) throws Exception {
      Message message = createMessage(sourceAddress.toString(), queueConfiguration.getAddress(), queueConfiguration.getName(), CREATE_QUEUE, queueConfiguration.toJSON());
      route(server, message);
   }

   @Override
   public void deleteQueue(SimpleString address, SimpleString queue) throws Exception {
      Message message = createMessage(sourceAddress.toString(), address, queue, DELETE_QUEUE, queue.toString());
      route(server, message);
   }

   @Override
   public void sendMessage(Message message, RoutingContext context, List<MessageReference> refs) {

      try {
         context.setReusable(false);
         Bindings bindings = server.getPostOffice().getBindingsForAddress(sourceAddress);
         for (Binding binding : bindings.getBindings()) {
            if (binding instanceof LocalQueueBinding) {
               LocalQueueBinding localQueueBinding = (LocalQueueBinding)binding;
               Queue transferQueue = localQueueBinding.getQueue();
               MessageReference ref = MessageReference.Factory.createReference(message, transferQueue);
               refs.add(ref);
               transferQueue.refUp(message);
            }
         }

      } catch (Throwable e) {
         logger.warn(e.getMessage(), e);
      }

   }

   public static Message createMessage(String to, SimpleString address, SimpleString queue, String event, String body) {
      Header header = new Header();
      header.setDurable(true);

      Map<Symbol, Object> annotations = new HashMap<>();
      annotations.put(EVENT_TYPE, event);
      MessageAnnotations messageAnnotations = new MessageAnnotations(annotations);

      HashMap<String, Object> apmap = new HashMap<>();
      apmap.put(ADDRESS, address.toString());
      if (queue != null) {
         apmap.put(QUEUE, queue.toString());
      }
      apmap.put(EVENT, event);
      ApplicationProperties applicationProperties = new ApplicationProperties(apmap);

      Properties properties = new Properties();
      properties.setTo(to);

      Section sectionBody = new AmqpValue(body);

      ByteBuf buffer = PooledByteBufAllocator.DEFAULT.heapBuffer(1024);

      try {
         EncoderImpl encoder = TLSEncode.getEncoder();
         encoder.setByteBuffer(new NettyWritable(buffer));
         encoder.writeObject(header);
         encoder.writeObject(messageAnnotations);
         encoder.writeObject(properties);
         encoder.writeObject(applicationProperties);
         encoder.writeObject(sectionBody);

         byte[] data = new byte[buffer.writerIndex()];
         buffer.readBytes(data);

         AMQPMessage amqpMessage = new AMQPStandardMessage(0, data, null);
         return amqpMessage;

      } finally {
         TLSEncode.getEncoder().setByteBuffer((WritableBuffer) null);
         buffer.release();
      }
   }

   public static void route(ActiveMQServer server, Message message) throws Exception {
      new Exception("Routing " + message).printStackTrace();
      message.setMessageID(server.getStorageManager().generateID());
      server.getPostOffice().route(message, new RemoteControlRouting(null) , false);
   }

   @Override
   public void routingDone(List<MessageReference> refs, boolean direct) {
      /*for (MessageReference ref : refs) {
         ref.getQueue().deliverAsync();
      }*/
   }

   private static class RemoteControlRouting extends RoutingContextImpl {

      public RemoteControlRouting(Transaction transaction) {
         super(transaction);
      }

      public RemoteControlRouting(Transaction transaction, Executor executor) {
         super(transaction, executor);
      }

      @Override
      public boolean isRemoteControl() {
         return true;
      }
   }
}
