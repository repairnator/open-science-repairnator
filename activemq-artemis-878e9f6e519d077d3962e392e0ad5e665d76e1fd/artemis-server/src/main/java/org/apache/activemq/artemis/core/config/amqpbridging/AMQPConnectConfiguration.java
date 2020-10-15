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
package org.apache.activemq.artemis.core.config.amqpbridging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.uri.ConnectorTransportConfigurationParser;

public class AMQPConnectConfiguration implements Serializable {
   private static final long serialVersionUID = 8026604526022462048L;

   String name;
   String uri;
   List<TransportConfiguration> transportConfigurations;

   List<AMQPConnectionAddress> connectionAddresses;

   AMQPReplica replica;

   public AMQPConnectConfiguration(String name, String uri) {
      this.name = name;
      this.uri = uri;
   }

   public AMQPConnectConfiguration addAddress(AMQPConnectionAddress amqpConnectionAddress) {
      if (connectionAddresses == null) {
         connectionAddresses = new ArrayList<>();
      }

      connectionAddresses.add(amqpConnectionAddress);

      return this;
   }

   public AMQPReplica getReplica() {
      return replica;
   }

   public AMQPConnectConfiguration setReplica(AMQPReplica replica) {
      this.replica = replica;
      return this;
   }

   public List<AMQPConnectionAddress> getConnectionAddresses() {
      return connectionAddresses;
   }

   public void parseURI() throws Exception {
      ConnectorTransportConfigurationParser parser = new ConnectorTransportConfigurationParser(false);
      this.transportConfigurations = parser.newObject(uri, name);
   }

   public List<TransportConfiguration> getTransportConfigurations() throws Exception {
      if (transportConfigurations == null) {
         parseURI();
      }
      return transportConfigurations;
   }

   public String getUri() {
      return uri;
   }

   public AMQPConnectConfiguration setUri(String uri) {
      this.uri = uri;
      return this;
   }

   public String getName() {
      return name;
   }

   public AMQPConnectConfiguration setName(String name) {
      this.name = name;
      return this;
   }
}
