package org.piangles.backbone.services.logging;

import org.piangles.backbone.services.Locator;

import org.piangles.backbone.services.logging.LoggingService;

public class NestedLoggingTest
{
	public static void main(String[] args)
	{
		
		System.setProperty("process.name", "LoggingTest");
		LoggingService logger = Locator.getInstance().getLoggingService();
		logger.info("NestedTestMessage1");
		LoggingCaller.log();
		System.out.println("Done!");
		System.exit(1);
	}

}
