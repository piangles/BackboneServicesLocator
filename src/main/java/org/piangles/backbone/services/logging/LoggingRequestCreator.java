package org.piangles.backbone.services.logging;

import java.lang.reflect.Method;
import java.util.UUID;

import com.TBD.backbone.services.logging.Category;
import com.TBD.backbone.services.logging.LogEvent;
import com.TBD.backbone.services.logging.SystemInfo;
import com.TBD.core.services.Header;
import com.TBD.core.services.Request;
import com.TBD.core.services.SourceInfo;
import com.TBD.core.services.remoting.Traceable;
import com.TBD.core.services.remoting.handlers.RequestCreator;
import com.TBD.core.util.ClassHelper;
import com.TBD.core.util.SystemHelper;

public class LoggingRequestCreator implements RequestCreator 
{
	private SystemInfo systemInfo = null;
	
	public LoggingRequestCreator()
	{
		String hostName = SystemHelper.getHostName();
		String loginId = SystemHelper.getLoginId();
		String processName = SystemHelper.getProcessName();
		String processId = SystemHelper.getProcessId();

		systemInfo = new SystemInfo(hostName, loginId, processName, processId);
	}

	@Override
	public Request createRequest(String userId, String sessionId, UUID traceId, String serviceName, Header header, Method method, Object[] args) throws Throwable
	{
		Request request = null;
		ClassHelper classHelper = new ClassHelper(5);
		
		//TODO solve the issue of creating many SourceInfo when logging is severe
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
			request = new Request(userId, sessionId, traceId, header, sourceInfo, serviceName, logEvent.getCategory().name(), args);
		}
		else
		{
			LogEvent event = null;
			String threadId = SystemHelper.getThreadId();
			Category category = Category.valueOf(method.getName().toUpperCase());
			if (args.length == 1)
			{
				event = new LogEvent(traceId, systemInfo.cloneAndCopy(threadId), category, classHelper.getClassName(), classHelper.getLineNumber(), args[0]);
			}
			else
			{
				event = new LogEvent(traceId, systemInfo.cloneAndCopy(threadId), category, classHelper.getClassName(), classHelper.getLineNumber(), args[0], (Throwable)args[1]);
			}
			args = new Object[]{event};
			
			request = new Request(userId, sessionId, traceId, header, sourceInfo, serviceName, method.getName(), args);
		}
		
		return request;
	}

}
