package org.piangles.backbone.services.session;

import org.piangles.core.services.Request;
import org.piangles.core.services.remoting.SessionValidator;

public final class PassThruSessionValidator implements SessionValidator
{
	@Override
	public boolean isSessionValid(Request request) throws Exception
	{
		return true;
	}

}
