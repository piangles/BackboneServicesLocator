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

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

import org.piangles.core.services.Header;
import org.piangles.core.services.Request;
import org.piangles.core.services.SourceInfo;
import org.piangles.core.services.remoting.Traceable;
import org.piangles.core.services.remoting.handlers.RequestCreator;
import org.piangles.core.util.ClassHelper;
import org.piangles.core.util.SystemHelper;

public class LoggingRequestCreator implements RequestCreator 
{
	private static final String CONSOLE_LOGGING = "console.logging";  
	private SystemInfo systemInfo = null;
	private ConsoleLogging consoleLogging = ConsoleLogging.None;
	
	public LoggingRequestCreator()
	{
		String hostName = SystemHelper.getHostName();
		String loginId = SystemHelper.getLoginId();
		String processName = SystemHelper.getProcessName();
		String processId = SystemHelper.getProcessId();

		systemInfo = new SystemInfo(hostName, loginId, processName, processId);
		String consoleLoggingStr = System.getenv(CONSOLE_LOGGING);
		try
		{
			if (consoleLoggingStr != null)
			{
				consoleLogging = ConsoleLogging.valueOf(consoleLoggingStr);
			}
		}
		catch(Exception e)
		{
			System.err.println(consoleLoggingStr + " is invalid value for " + CONSOLE_LOGGING + " environment variable. ");
			System.err.println(CONSOLE_LOGGING + " values must be one of : " + String.join(",", Arrays.stream(ConsoleLogging.values()).map(Enum::name).toArray(String[]::new)));
		}
		System.out.println("Console Logging set to: " + consoleLogging);
	}

	@Override
	public Request createRequest(String userId, String sessionId, UUID traceId, String serviceName, Header header, Method method, Object[] args) throws Throwable
	{
		Request request = null;
		ClassHelper classHelper = new ClassHelper(5);
		
		SourceInfo sourceInfo = new SourceInfo(classHelper.getClassName(), classHelper.getLineNumber(), 
				classHelper.getCompleteStackTrace(), null);
		
		/**
		 * First we need to identify - If the current thread is not Traceable 
		 * (implies it is not a RequestProcessor) or a Thread that is not handling
		 * a Request but is processing something needed by the service.
		 * 
		 *  Why : we need to keep track of what is traceable through traceId.
		 *  However if every LogEvent has a traceId then it clouds the DB. 
		 */
		Object currentThread = Thread.currentThread();
		if (!(currentThread instanceof Traceable))
		{
			traceId = null;
		}
		
		if ("RECORD".equals(method.getName().toUpperCase()))
		{
			LogEvent logEvent = (LogEvent)args[0];
			logToConsole(logEvent);
			request = new Request(userId, sessionId, traceId, header, sourceInfo, serviceName, logEvent.getCategory().name(), args);
		}
		else
		{
			LogEvent logEvent = null;
			String threadId = SystemHelper.getThreadId();
			Category category = Category.valueOf(method.getName().toUpperCase());
			if (args.length == 1)
			{
				logEvent = new LogEvent(traceId, systemInfo.cloneAndCopy(threadId), category, classHelper.getClassName(), classHelper.getLineNumber(), args[0]);
			}
			else
			{
				logEvent = new LogEvent(traceId, systemInfo.cloneAndCopy(threadId), category, classHelper.getClassName(), classHelper.getLineNumber(), args[0], (Throwable)args[1]);
			}
			logToConsole(logEvent);
			args = new Object[]{logEvent};
			
			request = new Request(userId, sessionId, traceId, header, sourceInfo, serviceName, method.getName(), args);
		}
		
		return request;
	}

	private void logToConsole(LogEvent logEvent)
	{
		if (consoleLogging != ConsoleLogging.None)
		{
			if (consoleLogging == ConsoleLogging.Brief)
			{
				System.out.println(logEvent.toBriefString());
			}
			else //Print Detailed
			{
				System.out.println(logEvent.toString());
			}
		}
	}
}
