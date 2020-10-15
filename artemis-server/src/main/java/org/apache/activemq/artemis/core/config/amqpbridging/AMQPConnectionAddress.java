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

/**
 * We will start small, with a matching address for all the addresses
 * each
 */
public class AMQPConnectionAddress {
   String matchAddress;
   String outboundQueueSuffix;
   boolean inbound;
   boolean outbound;

   // TODO: Routing Types

   public String getMatchAddress() {
      return matchAddress;
   }

   public AMQPConnectionAddress setMatchAddress(String matchAddress) {
      this.matchAddress = matchAddress;
      return this;
   }

   public String getOutboundQueueSuffix() {
      return outboundQueueSuffix;
   }

   public AMQPConnectionAddress setOutboundQueueSuffix(String outboundQueueSuffix) {
      this.outboundQueueSuffix = outboundQueueSuffix;
      return this;
   }

   public boolean isInbound() {
      return inbound;
   }

   public AMQPConnectionAddress setInbound(boolean inbound) {
      this.inbound = inbound;
      return this;
   }

   public boolean isOutbound() {
      return outbound;
   }

   public AMQPConnectionAddress setOutbound(boolean outbound) {
      this.outbound = outbound;
      return this;
   }
}
