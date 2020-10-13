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
package org.apache.activemq.artemis.tests.integration.amqp.connect;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.api.core.Message;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.CoreAddressConfiguration;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.apache.activemq.artemis.core.server.impl.AddressInfo;
import org.apache.activemq.artemis.protocol.amqp.bridge.AMQPRemoteControlSource;
import org.apache.activemq.artemis.tests.util.ActiveMQTestBase;
import org.apache.activemq.artemis.tests.util.CFUtil;
import org.junit.Assert;
import org.junit.Test;

public class RemoteControlBasicTest extends ActiveMQTestBase {

   ActiveMQServer server;
   public void setUp() throws Exception {
      super.setUp();

      Configuration configuration = createDefaultNettyConfig();
      configuration.addAddressConfiguration(new CoreAddressConfiguration().addRoutingType(RoutingType.ANYCAST).setName("REMOTECONTROL"));
      configuration.setRemoteControlAddress(SimpleString.toSimpleString("REMOTECONTROL"));
      server = addServer(ActiveMQServers.newActiveMQServer(configuration, true));
      // start the server
      server.start();
   }


   @Test
   public void testSend() throws Exception {
      ConnectionFactory factory = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:61616");
      Connection connection = factory.createConnection();
      Session session = connection.createSession(Session.AUTO_ACKNOWLEDGE);
      Queue queue = session.createQueue("myQueue");
      MessageConsumer consumer = session.createConsumer(queue);

      connection.start();

      MessageProducer producer = session.createProducer(queue);
      for (int i = 0; i < 10; i++) {
         producer.send(session.createTextMessage("hello"));
      }

      for (int i = 0; i < 10; i++) {
         Assert.assertNotNull(consumer.receive(1000));
      }

      connection.close();
   }


   /** this test will take the Message generated from remote control and send it through PostOffice
    *  to validate the format of the message and its delivery */
   @Test
   public void testDirectSend() throws Exception {
      server.addAddressInfo(new AddressInfo("test").addRoutingType(RoutingType.ANYCAST));
      server.createQueue(new QueueConfiguration("test").setAddress("test").setRoutingType(RoutingType.ANYCAST));

      Message message = AMQPRemoteControlSource.createMessage("test", SimpleString.toSimpleString("tt"), SimpleString.toSimpleString("ttt"), "test", "zzzz");
      AMQPRemoteControlSource.route(server, message);

      ConnectionFactory factory = CFUtil.createConnectionFactory("AMQP", "tcp://localhost:61616");
      Connection connection = factory.createConnection();
      Session session = connection.createSession(Session.AUTO_ACKNOWLEDGE);
      Queue queue = session.createQueue("test");
      connection.start();
      MessageConsumer consumer = session.createConsumer(queue);
      TextMessage txtMessage = (TextMessage)consumer.receive(5000);


      Assert.assertEquals("zzzz", txtMessage.getText());
      Assert.assertEquals("tt", txtMessage.getStringProperty("address"));
      Assert.assertEquals("tt", txtMessage.getStringProperty("address"));
      Assert.assertEquals("ttt", txtMessage.getStringProperty("queue"));
      Assert.assertEquals("test", txtMessage.getStringProperty("event"));

      connection.close();


   }

}
