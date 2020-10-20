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
		if (method.getName() == "fanOut")
		{
			/**
			 * Convert the Payload from Object to JSON String, this is because the Messaging
			 * Service does not have the dependency POJOs. So need to convert in Handler class
			 * from POJO to JSON.
			 */
			FanoutRequest fanoutRequest = (FanoutRequest)args[0];
			String payload = new String(JSON.getEncoder().encode(fanoutRequest.getEvent().getPayload()));
			fanoutRequest.getEvent().setPayload(payload);
			args = new Object[]{fanoutRequest};
		}

		return super.createRequest(userId, sessionId, traceId, serviceName, header, method, args);
	}
}
