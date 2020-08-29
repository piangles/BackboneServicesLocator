package com.TBD.backbone.services;

import com.TBD.backbone.services.logging.LoggingService;

public class NestedLoggingTest
{
	public static void main(String[] args)
	{
		
		System.setProperty("process.name", "LoggingTest");
		LoggingService logger = Tier2ServiceLocator.getInstance().getLoggingService();
		logger.info("NestedTestMessage1");
		LoggingCaller.log();
		System.out.println("Done!");
		System.exit(1);
	}

}
