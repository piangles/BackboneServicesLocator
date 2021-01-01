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
 
 
 
package org.piangles.backbone.services.config;

import org.piangles.backbone.services.Locator;
import org.piangles.core.test.AbstractServiceTestClient;

public class ConfigServiceTest extends AbstractServiceTestClient
{
	public static void main(String[] args) 
	{
		ConfigServiceTest test = new ConfigServiceTest();
		test.start();
	}
	
	@Override
	public void runImpl() throws Exception
	{
		ConfigService configService = Locator.getInstance().getConfigService();
		Configuration config;
		System.out.println("Calling getConfiguration...");
		config = configService.getConfiguration("14fe64ea-d15a-4c8b-af2f-f2c7efe1943b");
		System.out.println(config.getProperties());
	}
}
