package org.piangles.backbone.services.config;

import org.piangles.backbone.services.Locator;

import org.piangles.backbone.services.config.ConfigException;
import org.piangles.backbone.services.config.ConfigService;
import org.piangles.backbone.services.config.Configuration;
import org.piangles.core.services.remoting.SessionAwareable;
import org.piangles.core.services.remoting.SessionDetails;

public class ConfigServiceTest extends Thread implements SessionAwareable
{
	public static void main(String[] args) 
	{
		ConfigServiceTest test = new ConfigServiceTest();
		test.start();
	}
	
	public void run()
	{
		ConfigService configService = Locator.getInstance().getConfigService();
		Configuration config;
		try 
		{
			System.out.println("Calling getConfiguration...");
			config = configService.getConfiguration("14fe64ea-d15a-4c8b-af2f-f2c7efe1943b");
			System.out.println(config.getProperties());
		} 
		catch (ConfigException e) 
		{
			e.printStackTrace();
		}
		// ServiceLocator.getInstance().destroy();
		System.exit(0);
	}

	@Override
	public SessionDetails getSessionDetails()
	{
		return new SessionDetails("UserId", "TODOSessionId");
	}
}
