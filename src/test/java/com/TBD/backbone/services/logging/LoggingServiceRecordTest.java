package com.TBD.backbone.services;

import java.util.UUID;

import com.TBD.backbone.services.logging.Category;
import com.TBD.backbone.services.logging.LogEvent;
import com.TBD.backbone.services.logging.LoggingService;
import com.TBD.backbone.services.logging.SystemInfo;

public class LoggingServiceRecordTest
{
	public static void main(String[] args)
	{
		System.setProperty("process.name", "LoggingTest");
		LoggingService logger = Tier2ServiceLocator.getInstance().getLoggingService();
		
		SystemInfo systemInfo = new SystemInfo("Hostname", "Loginid", "ProcessName", "ProcessId");
		String threadId = Thread.currentThread().getName();
		Category category = Category.INFO;
		String className = LoggingServiceRecordTest.class.getName();
		String lineNumber = "21";

		
		LogEvent event = new LogEvent(UUID.randomUUID(), systemInfo.cloneAndCopy(threadId), category, className, lineNumber, null);
		logger.record(event);
		System.out.println("Done!");
		System.exit(1);
	}
}
