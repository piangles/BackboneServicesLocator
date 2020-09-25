package org.piangles.backbone.services.session;

import org.piangles.backbone.services.Locator;

import org.piangles.core.services.Request;
import org.piangles.core.services.remoting.SessionValidator;

public final class DefaultSessionValidator implements SessionValidator
{
	@Override
	public boolean isSessionValid(Request request) throws Exception
	{
		return Locator.getInstance().getSessionManagementService().isValid(request.getUserId(), request.getSessionId());
	}
}