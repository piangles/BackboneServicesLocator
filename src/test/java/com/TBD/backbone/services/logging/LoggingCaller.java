package com.TBD.backbone.services.logging;

import com.TBD.backbone.services.Locator;

public class LoggingCaller
{
	public static void log()
	{
		LoggingService logger = Locator.getInstance().getLoggingService();
		logger.info("NestedTestMessage2");
	}

}
