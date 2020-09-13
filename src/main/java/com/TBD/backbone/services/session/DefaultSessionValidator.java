package com.TBD.backbone.services.session;

import com.TBD.backbone.services.Locator;
import com.TBD.core.services.Request;
import com.TBD.core.services.remoting.SessionValidator;

public final class DefaultSessionValidator implements SessionValidator
{
	@Override
	public boolean isSessionValid(Request request) throws Exception
	{
		return Locator.getInstance().getSessionManagementService().isValid(request.getUserId(), request.getSessionId());
	}
}
