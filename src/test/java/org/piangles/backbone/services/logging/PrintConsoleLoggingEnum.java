package org.piangles.backbone.services.logging;

import java.util.Arrays;

public class PrintConsoleLoggingEnum
{

	public static void main(String[] args)
	{
		System.err.println("ConsoleLogging values must be one of : " +  String.join(",", Arrays.stream(ConsoleLogging.values()).map(Enum::name).toArray(String[]::new)));
		
		String canoClass = LogEvent.class.getCanonicalName();
		System.out.println(canoClass.substring(canoClass.lastIndexOf('.')+1));
	}

}
