package com.TBD.backbone.services.session;

import com.TBD.core.services.Request;
import com.TBD.core.services.remoting.SessionValidator;

public final class PassThruSessionValidator implements SessionValidator
{
	@Override
	public boolean isSessionValid(Request request) throws Exception
	{
		return true;
	}

}
