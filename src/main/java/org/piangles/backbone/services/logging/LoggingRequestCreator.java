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
		 * First we need to identify - If the current thread is Traceable 
		 * (implies it is a RequestProcessor) or just a Thread that is processing 
		 * on a separate thread.
		 * 
		 * If it is a Traceable thread, we continue to use the TracId, so we can keep
		 * track of the chain of events.
		 * 
		 * If it is not a Traceable thread, we will nullify the traceId. Else it is 
		 * misleading when viewing the records in the database. One thinks it is an events
		 * that is chained while in fact it is really a log from a separate Thread.
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
		else //The caller invoked debug, info, warn, error or fatal methods on LoggingService
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
			/**
			 * To make life easy we can log to console as well. The options for logging to console are
			 * defined in ConsoleLogging enum and by default is turned off. The environment variable
			 * console.logging should be set to Brief or Complete to enable console logging.
			 */
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
