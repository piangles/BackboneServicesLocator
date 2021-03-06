/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
 
 
package org.piangles.backbone.services.logging;

import java.util.UUID;

import org.piangles.backbone.services.Locator;

import org.piangles.backbone.services.logging.Category;
import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.backbone.services.logging.LoggingService;
import org.piangles.backbone.services.logging.SystemInfo;

public class LoggingServiceRecordTest
{
	public static void main(String[] args)
	{
		System.setProperty("process.name", "LoggingTest");
		LoggingService logger = Locator.getInstance().getLoggingService();
		
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
