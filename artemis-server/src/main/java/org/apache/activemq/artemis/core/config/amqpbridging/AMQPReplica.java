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

import org.apache.activemq.artemis.api.core.SimpleString;

public class AMQPReplica {

   final SimpleString address;

   final SimpleString subscription;

   /** if true, this server will push replication towards another server.
    *  if false, this server will pull replication from another server.
    *
    *  it's basically the direction where it's flowing. */
   final boolean push;

   public AMQPReplica(SimpleString address, SimpleString subscription, boolean push) {
      this.address = address;
      this.subscription = subscription;
      this.push = push;
   }

   public AMQPReplica(String address, String subscription, boolean push) {
      this(SimpleString.toSimpleString(address), SimpleString.toSimpleString(subscription), push);
   }

   public SimpleString getAddress() {
      return address;
   }

   public SimpleString getSubscription() {
      return subscription;
   }

   public boolean isPush() {
      return push;
   }

   @Override
   public String toString() {
      return "AMQPReplica{" + "address=" + address + ", subscription=" + subscription + ", push=" + push + '}';
   }
}
