package com.TBD.backbone.services.logging;

import com.TBD.backbone.services.Locator;

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
