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

import org.piangles.backbone.services.Locator;
import org.piangles.core.util.Logger;

/**
 * This class is an implementation of Logger interface and should not be
 * used directly. Configure the canonical name of this class in logging.class.
 * It will be leveraged by classes that do not have access to LoggingService
 * at compile time.
 *
 */
public final class LoggingServiceLogger extends Logger
{
	private LoggingService logger = null;
	
	public LoggingServiceLogger()
	{
		logger = Locator.getInstance().getLoggingService();
	}

	@Override
	public void debug(Object message)
	{
		logger.debug(message);
	}

	@Override
	public void debug(Object message, Throwable t)
	{
		logger.debug(message, t);
	}

	@Override
	public void info(Object message)
	{
		logger.info(message);
	}

	@Override
	public void info(Object message, Throwable t)
	{
		logger.info(message, t);
	}

	@Override
	public void warn(Object message)
	{
		logger.warn(message);
	}

	@Override
	public void warn(Object message, Throwable t)
	{
		logger.warn(message, t);
	}

	@Override
	public void error(Object message)
	{
		logger.error(message);
	}

	@Override
	public void error(Object message, Throwable t)
	{
		logger.error(message, t);
	}

	@Override
	public void fatal(Object message)
	{
		logger.fatal(message);
	}

	@Override
	public void fatal(Object message, Throwable t)
	{
		logger.fatal(message, t);
	}
}
