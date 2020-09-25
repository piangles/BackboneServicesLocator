package org.piangles.backbone.services.crypto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

import org.piangles.core.services.Header;
import org.piangles.core.services.Request;
import org.piangles.core.services.SourceInfo;
import org.piangles.core.util.ClassHelper;
import org.piangles.core.services.remoting.handlers.RequestCreator;

public final class CryptoServiceRequestCreator implements RequestCreator
{
	@Override
	public Request createRequest(String userId, String sessionId, UUID traceId, String serviceName, Header header, Method method, Object[] args) throws Throwable
	{
		SourceInfo sourceInfo = null;
		ClassHelper classHelper = new ClassHelper(5);
		String cipherAuthorizationId = null;
		Field cipherAuthorizationIdField = null;
		try
		{
			cipherAuthorizationIdField = Class.forName(classHelper.getClassName()).getDeclaredField("cipherAuthorizationId");
			cipherAuthorizationIdField.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Exception("Calling class needs to declate static cipherAuthorizationId for calling SecurityService cipher requests.");
		}
		cipherAuthorizationId = (String) cipherAuthorizationIdField.get(null);

		sourceInfo = new SourceInfo(classHelper.getClassName(), classHelper.getLineNumber(), classHelper.getCompleteStackTrace(), cipherAuthorizationId);
		return new Request(userId, sessionId, traceId, header, sourceInfo, serviceName, method.getName(), args);
	}
}