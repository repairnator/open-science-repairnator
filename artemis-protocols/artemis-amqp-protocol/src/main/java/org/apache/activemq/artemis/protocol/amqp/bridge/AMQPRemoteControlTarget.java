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

import java.util.List;
import java.util.Map;

import org.apache.activemq.artemis.api.core.Message;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.MessageReference;
import org.apache.activemq.artemis.core.server.RoutingContext;
import org.apache.activemq.artemis.core.server.impl.AddressInfo;
import org.apache.activemq.artemis.core.server.remotecontrol.RemoteControl;
import org.apache.activemq.artemis.core.transaction.Transaction;
import org.apache.activemq.artemis.protocol.amqp.broker.AMQPMessage;
import org.apache.activemq.artemis.protocol.amqp.broker.AMQPSessionCallback;
import org.apache.activemq.artemis.protocol.amqp.proton.AMQPConnectionContext;
import org.apache.activemq.artemis.protocol.amqp.proton.AMQPSessionContext;
import org.apache.activemq.artemis.protocol.amqp.proton.ProtonAbstractReceiver;
import org.apache.qpid.proton.amqp.Symbol;
import org.apache.qpid.proton.amqp.messaging.AmqpValue;
import org.apache.qpid.proton.amqp.transport.ReceiverSettleMode;
import org.apache.qpid.proton.engine.Delivery;
import org.apache.qpid.proton.engine.Receiver;
import org.jboss.logging.Logger;

public class AMQPRemoteControlTarget extends ProtonAbstractReceiver implements RemoteControl {

   private static final Logger logger = Logger.getLogger(AMQPRemoteControlTarget.class);

   final ActiveMQServer server;

   public AMQPRemoteControlTarget(AMQPSessionCallback sessionSPI,
                                  AMQPConnectionContext connection,
                                  AMQPSessionContext protonSession,
                                  Receiver receiver,
                                  ActiveMQServer server) {
      super(sessionSPI, connection, protonSession, receiver);
      this.server = server;
   }

   @Override
   public void flow() {
      creditRunnable.run();
   }

   @Override
   protected void actualDelivery(AMQPMessage message, Delivery delivery, Receiver receiver, Transaction tx) {
      Map<Symbol, Object> annotationsMap = message.getMessageAnnotationsMap(false);
      incrementSettle();


      System.out.println("*******************************************************************************************************************************\n" +
                         "Received " + message + "\n" +
                         "*******************************************************************************************************************************");
      try {
         String eventType = (String)annotationsMap.get(AMQPRemoteControlSource.EVENT_TYPE);
         if (eventType != null) {
            // I'm not using fancy switch with strings for JDK compatibility, just in case
            if (eventType.equals(AMQPRemoteControlSource.ADD_ADDRESS)) {
               AddressInfo addressInfo = parseAddress(message);
               addAddress(addressInfo);
            }  else if (eventType.equals(AMQPRemoteControlSource.DELETE_ADDRESS)) {
               AddressInfo addressInfo = parseAddress(message);
               deleteAddress(addressInfo);
            } else if (eventType.equals(AMQPRemoteControlSource.CREATE_QUEUE)) {
               QueueConfiguration queueConfiguration = parseQueue(message);
               createQueue(queueConfiguration);
            } else if (eventType.equals(AMQPRemoteControlSource.DELETE_QUEUE)) {
               Map<String, Object> applicationProperties = message.getApplicationPropertiesMap(false);
               String address = (String)applicationProperties.get(AMQPRemoteControlSource.ADDRESS);
               String queueName = (String)applicationProperties.get(AMQPRemoteControlSource.QUEUE);
               deleteQueue(SimpleString.toSimpleString(address), SimpleString.toSimpleString(queueName));
            }
         } else {
            if (message.getMessageID() <= 0) {
               message.setMessageID(server.getStorageManager().generateID());
            }
            server.getPostOffice().route(message, false);
            flow();
         }
      } catch (Throwable e) {
         logger.warn(e.getMessage(), e);
      } finally {
         try {
            settle(delivery);
            connection.flush();
         } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
         }
      }
   }

   @Override
   public void initialize() throws Exception {
      super.initialize();
      org.apache.qpid.proton.amqp.messaging.Target target = (org.apache.qpid.proton.amqp.messaging.Target) receiver.getRemoteTarget();

      // Match the settlement mode of the remote instead of relying on the default of MIXED.
      receiver.setSenderSettleMode(receiver.getRemoteSenderSettleMode());

      // We don't currently support SECOND so enforce that the answer is anlways FIRST
      receiver.setReceiverSettleMode(ReceiverSettleMode.FIRST);
      flow();
   }


   private QueueConfiguration parseQueue(AMQPMessage message) throws Exception {
      AmqpValue bodyvalue = (AmqpValue) message.getBody();
      String body = (String) bodyvalue.getValue();
      QueueConfiguration queueConfiguration = QueueConfiguration.fromJSON(body);
      return queueConfiguration;
   }

   private AddressInfo parseAddress(AMQPMessage message) throws Exception {
      AmqpValue bodyvalue = (AmqpValue) message.getBody();
      String body = (String) bodyvalue.getValue();
      AddressInfo addressInfo = AddressInfo.fromJSON(body);
      return addressInfo;
   }

   @Override
   public void addAddress(AddressInfo addressInfo) throws Exception {
      System.out.println("*******************************************************************************************************************************");
      System.out.println("Adding address on the other side..." + addressInfo);
      server.addAddressInfo(addressInfo);
   }

   @Override
   public void deleteAddress(AddressInfo addressInfo) throws Exception {
      System.out.println("*******************************************************************************************************************************");
      System.out.println("delete address " + addressInfo);
      server.removeAddressInfo(addressInfo.getName(), null, true);
   }

   @Override
   public void createQueue(QueueConfiguration queueConfiguration) throws Exception {
      System.out.println("*******************************************************************************************************************************");
      System.out.println("Adding queue " + queueConfiguration);
      server.createQueue(queueConfiguration, true);
   }

   @Override
   public void deleteQueue(SimpleString addressName, SimpleString queueName) throws Exception {
      System.out.println("*******************************************************************************************************************************");
      System.out.println("destroy queue " + queueName);
      server.destroyQueue(queueName);
   }

   @Override
   public void routingDone(List<MessageReference> refs, boolean direct) {

   }

   @Override
   public void sendMessage(Message message, RoutingContext context, List<MessageReference> refs) {

   }
}
