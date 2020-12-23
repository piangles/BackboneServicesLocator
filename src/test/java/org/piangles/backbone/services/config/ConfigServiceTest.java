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
