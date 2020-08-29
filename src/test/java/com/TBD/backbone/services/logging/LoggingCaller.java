package com.TBD.backbone.services;

import com.TBD.backbone.services.logging.LoggingService;

public class LoggingCaller
{
	public static void log()
	{
		LoggingService logger = Tier2ServiceLocator.getInstance().getLoggingService();
		logger.info("NestedTestMessage2");
	}

}
