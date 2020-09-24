package org.piangles.backbone.services.logging;

import org.piangles.backbone.services.Locator;

import com.TBD.backbone.services.logging.LoggingService;

public class LoggingCaller
{
	public static void log()
	{
		LoggingService logger = Locator.getInstance().getLoggingService();
		logger.info("NestedTestMessage2");
	}

}
