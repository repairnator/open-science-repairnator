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

package org.apache.activemq.artemis.tests.integration.amqp.connect;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.core.config.CoreAddressConfiguration;
import org.apache.activemq.artemis.core.config.amqpbridging.AMQPConnectConfiguration;
import org.apache.activemq.artemis.core.config.amqpbridging.AMQPConnectionAddress;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.impl.AddressInfo;
import org.apache.activemq.artemis.tests.integration.amqp.AmqpClientTestSupport;
import org.apache.activemq.artemis.tests.util.CFUtil;
import org.apache.activemq.artemis.utils.Wait;
import org.junit.Assert;
import org.junit.Test;

public class AMQPBridgeTest extends AmqpClientTestSupport {

   protected static final int AMQP_PORT_2 = 5673;

   ActiveMQServer server_2;


   @Test
   public void testsSimpleConnect() throws Exception {
      server_2 = createServer(AMQP_PORT_2, false);

      AMQPConnectConfiguration amqpConnection = new AMQPConnectConfiguration("test", "tcp://localhost:" + AMQP_PORT);
      server_2.getConfiguration().addAMQPConnection(amqpConnection);

      server_2.start();
   }

   @Test
   public void testSimpleTransferOutbound() throws Exception {
      server.setIdentity("targetServer");
      server.addAddressInfo(new AddressInfo(SimpleString.toSimpleString("TEST"), RoutingType.ANYCAST));
      server.createQueue(new QueueConfiguration("TEST").setRoutingType(RoutingType.ANYCAST));

      server_2 = createServer(AMQP_PORT_2, false);

      AMQPConnectConfiguration amqpConnection = new AMQPConnectConfiguration("test", "tcp://localhost:" + AMQP_PORT);
      amqpConnection.addAddress(new AMQPConnectionAddress().setMatchAddress("TEST").setOutbound(true).setInbound(false));
      server_2.getConfiguration().addAMQPConnection(amqpConnection);
      server_2.getConfiguration().addAddressConfiguration(new CoreAddressConfiguration().setName("TEST").addRoutingType(RoutingType.ANYCAST));
      server_2.getConfiguration().addQueueConfiguration(new QueueConfiguration("TEST").setRoutingType(RoutingType.ANYCAST));
      server_2.setIdentity("serverWithBridge");

      server_2.start();
      Wait.assertTrue(server_2::isStarted);

      ConnectionFactory factory = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:" + AMQP_PORT_2);
      Connection connection = factory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(session.createQueue("TEST"));
      producer.setDeliveryMode(DeliveryMode.PERSISTENT);
      String largeMessageBody = null;
      for (int i = 0; i < 30; i++) {
         if (i == 0) {
            StringBuffer buffer = new StringBuffer();
            for (int s = 0; s < 10024; s++) {
               buffer.append("*******************************************************************************************************************************");
            }
            largeMessageBody = buffer.toString();
            TextMessage message = session.createTextMessage(buffer.toString());
            producer.send(message);
         } else {
            producer.send(session.createMessage());
         }
      }

      ConnectionFactory factory2 = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:" + AMQP_PORT);
      Connection connection2 = factory2.createConnection();
      Session session2 = connection2.createSession(false, Session.AUTO_ACKNOWLEDGE);
      connection2.start();

      MessageConsumer consumer = session2.createConsumer(session2.createQueue("TEST"));
      for (int i = 0; i < 30; i++) {
         Message message = consumer.receive(5000);
         if (message instanceof TextMessage) {
            if (message instanceof TextMessage) {
               Assert.assertEquals(largeMessageBody, ((TextMessage)message).getText());
            } else {
               System.out.println("i = " + i);
            }
         }
      }
      Assert.assertNull(consumer.receiveNoWait());


   }


   @Test
   public void testSimpleTransferInbound() throws Exception {
      server.setIdentity("targetServer");
      server.addAddressInfo(new AddressInfo(SimpleString.toSimpleString("TEST"), RoutingType.ANYCAST));
      server.createQueue(new QueueConfiguration("TEST").setRoutingType(RoutingType.ANYCAST));

      server_2 = createServer(AMQP_PORT_2, false);

      AMQPConnectConfiguration amqpConnection = new AMQPConnectConfiguration("test", "tcp://localhost:" + AMQP_PORT);
      amqpConnection.addAddress(new AMQPConnectionAddress().setMatchAddress("TEST").setOutbound(false).setInbound(true));
      server_2.getConfiguration().addAMQPConnection(amqpConnection);
      server_2.getConfiguration().addAddressConfiguration(new CoreAddressConfiguration().setName("TEST").addRoutingType(RoutingType.ANYCAST));
      server_2.getConfiguration().addQueueConfiguration(new QueueConfiguration("TEST").setRoutingType(RoutingType.ANYCAST));
      server_2.setIdentity("serverWithBridge");

      server_2.start();
      Wait.assertTrue(server_2::isStarted);

      ConnectionFactory factory = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:" + AMQP_PORT);
      Connection connection = factory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(session.createQueue("TEST"));
      producer.setDeliveryMode(DeliveryMode.PERSISTENT);
      String largeMessageBody = null;
      for (int i = 0; i < 30; i++) {
         if (i == 0) {
            StringBuffer buffer = new StringBuffer();
            for (int s = 0; s < 10024; s++) {
               buffer.append("*******************************************************************************************************************************");
            }
            largeMessageBody = buffer.toString();
            TextMessage message = session.createTextMessage(buffer.toString());
            producer.send(message);
         } else {
            producer.send(session.createMessage());
         }
      }

      ConnectionFactory factory2 = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:" + AMQP_PORT_2);
      Connection connection2 = factory2.createConnection();
      Session session2 = connection2.createSession(false, Session.AUTO_ACKNOWLEDGE);
      connection2.start();

      MessageConsumer consumer = session2.createConsumer(session2.createQueue("TEST"));
      for (int i = 0; i < 30; i++) {
         Message message = consumer.receive(5000);
         if (message instanceof TextMessage) {
            if (message instanceof TextMessage) {
               Assert.assertEquals(largeMessageBody, ((TextMessage)message).getText());
            } else {
               System.out.println("i = " + i);
            }
         }
      }
      Assert.assertNull(consumer.receiveNoWait());
   }

   @Test
   public void testFailedConnection() throws Exception {
      // TODO implement this test properly
      server_2 = createServer(AMQP_PORT_2, false);
      server_2.getConfiguration().addConnectorConfiguration("amqp", "tcp://localhost:" + AMQP_PORT);
      server_2.getConfiguration().addConnectorConfiguration("amqp", "tcp://localhost:" + AMQP_PORT);

      AMQPConnectConfiguration amqpConnection = new AMQPConnectConfiguration("test", "tcp://test:61616");
      server_2.getConfiguration().addAMQPConnection(amqpConnection);

      server_2.start();
   }

}
