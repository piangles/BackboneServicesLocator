/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
 
 
package org.piangles.backbone.services.msg;

import java.lang.reflect.Method;
import java.util.UUID;

import org.piangles.core.services.Header;
import org.piangles.core.services.Request;
import org.piangles.core.services.remoting.handlers.DefaultRequestCreator;
import org.piangles.core.util.coding.JSON;

public class MessagingRequestCreator extends DefaultRequestCreator
{
	@Override
	public Request createRequest(String userId, String sessionId, UUID traceId, String serviceName, Header header, Method method, Object[] args) throws Throwable
	{
		/**
		 * Convert the Payload from Object to JSON String, this is because the Messaging
		 * Service does not have the dependency POJOs. So need to convert in Handler class
		 * from POJO to JSON.
		 */
		if (method.getName() == "fanOut")
		{
			FanoutRequest fanoutRequest = (FanoutRequest)args[0];
			String payload = new String(JSON.getEncoder().encode(fanoutRequest.getEvent().getPayload()));
			fanoutRequest.getEvent().setPayload(payload);
			args = new Object[]{fanoutRequest};
		}
		else if (method.getName().equals("publish"))
		{
			/**
			 * There are 2 publish overloaded methods with different parameters.
			 * So need to figure out where the Event object is in params list.
			 */
			Event event = null;
			if (args.length == 2)//The publish method for topic and Event
			{
				event = (Event)args[1];
				String payload = new String(JSON.getEncoder().encode(event.getPayload()));
				event.setPayload(payload);
				args = new Object[]{args[0], event};
			}
			else if (args.length == 3)//The publish method for entityType, entity and Event
			{
				event = (Event)args[2];
				String payload = new String(JSON.getEncoder().encode(event.getPayload()));
				event.setPayload(payload);
				args = new Object[]{args[0], args[1], event};
			}
		}

		return super.createRequest(userId, sessionId, traceId, serviceName, header, method, args);
	}
}
