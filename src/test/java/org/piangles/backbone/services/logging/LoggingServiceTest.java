package org.piangles.backbone.services.logging;

import org.piangles.backbone.services.Locator;

import org.piangles.backbone.services.logging.LoggingService;
import org.piangles.core.services.remoting.SessionAwareable;
import org.piangles.core.services.remoting.SessionDetails;

public class LoggingServiceTest extends Thread implements SessionAwareable
{
	public static void main(String[] args)
	{
		LoggingServiceTest test = new LoggingServiceTest();
		test.start();
	}
	
	public void run()
	{
		System.setProperty("process.name", "LoggingTest");
		LoggingService logger = Locator.getInstance().getLoggingService();
		logger.info(null);
		logger.info("TestMessage");
		logger.info("TestMessage", new Exception());
		System.out.println("Done!");
		System.exit(1);
	}
	

	@Override
	public SessionDetails getSessionDetails()
	{
		return new SessionDetails("UserId", "TODOSessionId");
	}
}
