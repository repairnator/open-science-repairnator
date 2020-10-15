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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.core.config.amqpbridging.AMQPConnectConfiguration;
import org.apache.activemq.artemis.core.config.amqpbridging.AMQPReplica;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.tests.integration.amqp.AmqpClientTestSupport;
import org.apache.activemq.artemis.tests.util.CFUtil;
import org.apache.activemq.artemis.utils.Wait;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AMQPReplicaTest extends AmqpClientTestSupport {

   protected static final int AMQP_PORT_2 = 5673;

   ActiveMQServer server_2;

   @Ignore // not implemented yet
   @Test
   public void testReplicaWithPull() throws Exception {
   }

   @Ignore // not implemented yet
   @Test
   public void testReplicaDisconnect() throws Exception {
   }

   @Ignore // not implemented yet
   @Test
   public void testReplicaCatchupOnQueueCreates() throws Exception {

   }
   @Test
   public void testWithLargeMessage() throws Exception {
      replicaTest(true, true);
   }

   @Test
   public void testReplicaWithPush() throws Exception {
      replicaTest(true, false);
   }

   private String getText(boolean large, int i) {
      if (!large) {
         return "Text " + i;
      } else {
         StringBuffer buffer = new StringBuffer();
         while (buffer.length() < 110 * 1024) {
            buffer.append("Text " + i + " ");
         }
         return buffer.toString();
      }
   }

   private void replicaTest(boolean push, boolean largeMessage) throws Exception {
      server.setIdentity("targetServer");
      //server.addAddressInfo(new AddressInfo(SimpleString.toSimpleString("TEST"), RoutingType.ANYCAST));
      //server.createQueue(new QueueConfiguration("TEST").setRoutingType(RoutingType.ANYCAST));

      /** if (!push) { -- TODO configure this on the server, not here though!
         AMQPConnectConfiguration amqpConnection = new AMQPConnectConfiguration("test", "tcp://localhost:" + AMQP_PORT_2);
         amqpConnection.setReplica(new AMQPReplica("REPLICA", "SERVER2", false));
      } */

      server_2 = createServer(AMQP_PORT_2, false);

      if (push) {
         AMQPConnectConfiguration amqpConnection = new AMQPConnectConfiguration("test", "tcp://localhost:" + AMQP_PORT);
         amqpConnection.setReplica(new AMQPReplica("REPLICA", "SUBSCRIPTION_ON_SERVER", true));
         server_2.getConfiguration().addAMQPConnection(amqpConnection);
      }

      int NUMBER_OF_MESSAGES = 100;

      server_2.start();
      Wait.assertTrue(server_2::isStarted);

      ConnectionFactory factory = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:" + AMQP_PORT_2);
      Connection connection = factory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(session.createQueue("TEST"));
      producer.setDeliveryMode(DeliveryMode.PERSISTENT);
      for (int i = 0; i < NUMBER_OF_MESSAGES; i++) {
         Message message = session.createTextMessage(getText(largeMessage, i));
         message.setIntProperty("i", i);
         producer.send(message);
      }

      // Now we need to stop the server, and make it activate

      consumeMessages(largeMessage, NUMBER_OF_MESSAGES, AMQP_PORT);
      consumeMessages(largeMessage, NUMBER_OF_MESSAGES, AMQP_PORT_2); // We consume on both servers as this is currently replicated

   }

   private void consumeMessages(boolean largeMessage, int NUMBER_OF_MESSAGES, int port) throws JMSException {
      ConnectionFactory cf = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:" + port);
      Connection conn = cf.createConnection();
      Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
      conn.start();

      MessageConsumer consumer = sess.createConsumer(sess.createQueue("TEST"));
      for (int i = 0; i < NUMBER_OF_MESSAGES; i++) {
         Message message = consumer.receive(3000);
         Assert.assertNotNull(message);
         if (message instanceof TextMessage) {
            Assert.assertEquals(getText(largeMessage, i), ((TextMessage)message).getText());
         }
      }
      Assert.assertNull(consumer.receiveNoWait());
      conn.close();
   }

}
