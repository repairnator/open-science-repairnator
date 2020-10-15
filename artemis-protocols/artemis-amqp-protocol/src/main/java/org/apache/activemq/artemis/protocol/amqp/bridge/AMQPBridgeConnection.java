/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.activemq.artemis.protocol.amqp.bridge;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.config.amqpbridging.AMQPConnectConfiguration;
import org.apache.activemq.artemis.core.config.amqpbridging.AMQPConnectionAddress;
import org.apache.activemq.artemis.core.config.amqpbridging.AMQPReplica;
import org.apache.activemq.artemis.core.postoffice.Binding;
import org.apache.activemq.artemis.core.postoffice.QueueBinding;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnection;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnector;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;
import org.apache.activemq.artemis.core.server.ActiveMQComponent;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.Consumer;
import org.apache.activemq.artemis.core.server.Queue;
import org.apache.activemq.artemis.core.server.impl.AddressInfo;
import org.apache.activemq.artemis.protocol.amqp.broker.AMQPSessionCallback;
import org.apache.activemq.artemis.protocol.amqp.broker.ActiveMQProtonRemotingConnection;
import org.apache.activemq.artemis.protocol.amqp.broker.ProtonProtocolManager;
import org.apache.activemq.artemis.protocol.amqp.proton.AMQPSessionContext;
import org.apache.activemq.artemis.protocol.amqp.proton.ProtonServerSenderContext;
import org.apache.activemq.artemis.protocol.amqp.proton.SenderInitializer;
import org.apache.activemq.artemis.protocol.amqp.sasl.ClientSASL;
import org.apache.activemq.artemis.spi.core.protocol.ConnectionEntry;
import org.apache.activemq.artemis.spi.core.remoting.ClientConnectionLifeCycleListener;
import org.apache.activemq.artemis.spi.core.remoting.ClientProtocolManager;
import org.apache.activemq.artemis.spi.core.remoting.Connection;
import org.apache.activemq.artemis.utils.ConfigurationHelper;
import org.apache.qpid.proton.amqp.Symbol;
import org.apache.qpid.proton.amqp.messaging.Source;
import org.apache.qpid.proton.amqp.messaging.Target;
import org.apache.qpid.proton.engine.Receiver;
import org.apache.qpid.proton.engine.Sender;
import org.apache.qpid.proton.engine.Session;
import org.jboss.logging.Logger;

public class AMQPBridgeConnection implements ClientConnectionLifeCycleListener {

   public static final Symbol REPLICA_TARGET_SYMBOL = Symbol.valueOf("_AMQ_REPLICA_TARGET");
   private static final Logger logger = Logger.getLogger(AMQPBridgeConnection.class);

   private final AMQPConnectConfiguration amqpConfiguration;
   private final ProtonProtocolManager protonProtocolManager;
   private final ActiveMQServer server;
   private final NettyConnector bridgesConnector;
   private NettyConnection connection;
   private Session session;
   AMQPSessionContext sessionContext;
   ActiveMQProtonRemotingConnection protonRemotingConnection;
   private volatile boolean started = false;

   public AMQPBridgeConnection(AMQPConnectConfiguration amqpConfiguration,
                               ProtonProtocolManager protonProtocolManager,
                               ActiveMQServer server,
                               NettyConnector bridgesConnector) {
      this.amqpConfiguration = amqpConfiguration;
      this.protonProtocolManager = protonProtocolManager;
      this.server = server;
      this.bridgesConnector = bridgesConnector;
   }

   public NettyConnection connect() throws Exception {

      try {
         List<TransportConfiguration> configurationList = amqpConfiguration.getTransportConfigurations();

         AMQPBridgeManager.ClientProtocolManagerWithAMQP protonFacade = new AMQPBridgeManager.ClientProtocolManagerWithAMQP(protonProtocolManager);

         TransportConfiguration tpConfig = configurationList.get(0);

         String host = ConfigurationHelper.getStringProperty(TransportConstants.HOST_PROP_NAME, TransportConstants.DEFAULT_HOST, tpConfig.getParams());
         int port = ConfigurationHelper.getIntProperty(TransportConstants.PORT_PROP_NAME, TransportConstants.DEFAULT_PORT, tpConfig.getParams());
         connection = bridgesConnector.createConnection(null, host, port);

         if (connection == null) {
            logger.warn("\n*******************************************************************************************************************************\n" +
                        "AMQPBridgeConnect Cannot connect towards " + host + " :: " + port + "\n" +
                        "*******************************************************************************************************************************");
         }

         ConnectionEntry entry = protonProtocolManager.createOutgoingConnectionEntry(connection);
         protonRemotingConnection = (ActiveMQProtonRemotingConnection) entry.connection;
         connection.getChannel().pipeline().addLast(new BridgeChannelHandler(bridgesConnector.getChannelGroup(), protonRemotingConnection.getAmqpConnection().getHandler()));

         protonRemotingConnection.getAmqpConnection().runLater(() -> {
            protonRemotingConnection.getAmqpConnection().open();
            protonRemotingConnection.getAmqpConnection().flush();
         });

         session = protonRemotingConnection.getAmqpConnection().getHandler().getConnection().session();
         sessionContext = protonRemotingConnection.getAmqpConnection().getSessionExtension(session);
         protonRemotingConnection.getAmqpConnection().runLater(() -> {
            session.open();
            protonRemotingConnection.getAmqpConnection().flush();
         });

         session.open();
         protonRemotingConnection.getAmqpConnection().getHandler().flush();

         if (amqpConfiguration.getConnectionAddresses() != null) {
            for (AMQPConnectionAddress policy : amqpConfiguration.getConnectionAddresses()) {
               Collection<Binding> bindings = server.getPostOffice().getMatchingBindings(SimpleString.toSimpleString(policy.getMatchAddress()));
               for (Binding b : bindings) {
                  if (b instanceof QueueBinding) {
                     if (policy.isOutbound()) {
                        connectOutbound((QueueBinding) b);
                     }

                     if (policy.isInbound()) {
                        connectInbound(protonRemotingConnection, session, sessionContext, (QueueBinding) b);
                     }

                  }
               }
            }
         }

         if (amqpConfiguration.getReplica() != null) {
            if (!amqpConfiguration.getReplica().isPush()) {
               logger.warn("Replica pull is not implemented yet");
            } else {
               configurePushReplica(amqpConfiguration.getReplica());
            }
         }

         protonRemotingConnection.getAmqpConnection().flush();
         return connection;
      } catch (Throwable e) {
         error(e);
         return null;
      }
   }

   private void configurePushReplica(AMQPReplica replicaConfig) throws Exception {
      AddressInfo addressInfo = server.getAddressInfo(replicaConfig.getAddress());
      if (addressInfo == null) {
         addressInfo = new AddressInfo(replicaConfig.getAddress()).addRoutingType(RoutingType.MULTICAST);
      }

      server.createQueue(new QueueConfiguration(replicaConfig.getSubscription()).setAddress(replicaConfig.getAddress()).setRoutingType(RoutingType.MULTICAST), true);


      QueueBinding queueBinding = (QueueBinding)server.getPostOffice().getBinding(replicaConfig.getSubscription());
      if (queueBinding == null) {
         logger.warn("Queue does not exist even after creation! " + replicaConfig);
         return;
      }

      Queue queue = queueBinding.getQueue();

      if (!queue.getAddress().equals(replicaConfig.getAddress())) {
         logger.warn("Queue " + queue + " belong to a different address (" + queue.getAddress() + "), while we expected it to be " + addressInfo.getName());
         return;
      }

      connectOutbound(queueBinding, REPLICA_TARGET_SYMBOL);

      server.installRemoteControl(new AMQPRemoteControlSource(replicaConfig.getAddress(), server));
   }

   private void connectInbound(ActiveMQProtonRemotingConnection protonRemotingConnection,
                                Session session,
                                AMQPSessionContext sessionContext,
                                QueueBinding b) {
      if (logger.isDebugEnabled()) {
         logger.debug("Connecting inbound for " + b);
      }
      QueueBinding queueBinding = b;
      Receiver receiver = session.receiver(queueBinding.getQueue().getName().toString());
      Target target = new Target();
      target.setAddress(queueBinding.getAddress().toString());
      receiver.setTarget(target);

      Source source = new Source();
      source.setAddress(queueBinding.getQueue().getName().toString());
      receiver.setSource(source);

      protonRemotingConnection.getAmqpConnection().runLater(() -> {
         receiver.open();
         protonRemotingConnection.getAmqpConnection().flush();
         try {
            sessionContext.addReceiver(receiver);
         } catch (Exception e) {
            error(e);
         }
      });
   }


   private void connectOutbound(QueueBinding b,
                                Symbol... capabilities) {
      // TODO: Adding log.debug here
      if (logger.isDebugEnabled()) {
         logger.debug("Connecting outbound for " + b);
      }
      QueueBinding queueBinding = b;
      Sender sender = session.sender(queueBinding.getQueue().getName().toString());
      Target target = new Target();
      target.setAddress(queueBinding.getAddress().toString());
      sender.setTarget(target);

      Source source = new Source();
      source.setAddress(queueBinding.getQueue().getName().toString());
      sender.setSource(source);

      if (capabilities != null && capabilities.length > 0) {
         sender.setDesiredCapabilities(capabilities);
      }

      BridgeSenderInitializer bridgeSenderInitializer = new BridgeSenderInitializer(queueBinding, sender, sessionContext.getSessionSPI());

      protonRemotingConnection.getAmqpConnection().runLater(() -> {
         try {
            sessionContext.addSender(sender, bridgeSenderInitializer);
         } catch (Exception e) {
            error(e);
         }
         protonRemotingConnection.getAmqpConnection().flush();
      });
   }

   protected void error(Throwable e) {
      e.printStackTrace();
      // TODO: Make this async
      //       and make this retry
   }

   private class BridgeSenderInitializer implements SenderInitializer {

      final QueueBinding binding;
      final Sender sender;
      final AMQPSessionCallback sessionSPI;

      BridgeSenderInitializer(Binding binding, Sender sender, AMQPSessionCallback sessionSPI) {
         this.binding = (QueueBinding)binding;
         this.sessionSPI = sessionSPI;
         this.sender = sender;
      }

      @Override
      public boolean isPresettle() {
         return false;
      }

      @Override
      public Consumer init(ProtonServerSenderContext senderContext) throws Exception {
         SimpleString queue = binding.getQueue().getName();
         return (Consumer) sessionSPI.createSender(senderContext, queue, null, false);
      }

      @Override
      public void close() throws Exception {
         // TODO implement close
      }
   }

   public void disconnect() throws Exception {

      // TODO remove from queues just like a connection close

   }

   @Override
   public void connectionCreated(ActiveMQComponent component, Connection connection, ClientProtocolManager protocol) {
      System.out.println("Connection created on " + protonProtocolManager.getServer().getIdentity());

      // reset connection retry counts

   }

   @Override
   public void connectionDestroyed(Object connectionID) {
      System.out.println("connection destroyed");
   }

   @Override
   public void connectionException(Object connectionID, ActiveMQException me) {
      me.printStackTrace();
      // TODO: retry connection

   }

   @Override
   public void connectionReadyForWrites(Object connectionID, boolean ready) {

   }

   private static class PlainSASLMechanism implements ClientSASL {

      private final byte[] initialResponse;

      PlainSASLMechanism(String username, String password) {
         byte[] usernameBytes = username.getBytes(StandardCharsets.UTF_8);
         byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
         byte[] encoded = new byte[usernameBytes.length + passwordBytes.length + 2];
         System.arraycopy(usernameBytes, 0, encoded, 1, usernameBytes.length);
         System.arraycopy(passwordBytes, 0, encoded, usernameBytes.length + 2, passwordBytes.length);
         initialResponse = encoded;
      }

      @Override
      public String getName() {
         return "PLAIN";
      }

      @Override
      public byte[] getInitialResponse() {
         return initialResponse;
      }

      @Override
      public byte[] getResponse(byte[] challenge) {
         return new byte[0];
      }
   }

}
