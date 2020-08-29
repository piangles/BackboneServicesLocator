package com.TBD.backbone.services;

import com.TBD.backbone.services.config.ConfigException;
import com.TBD.backbone.services.config.ConfigService;
import com.TBD.backbone.services.config.Configuration;
import com.TBD.core.services.remoting.SessionAwareable;
import com.TBD.core.services.remoting.SessionDetails;

public class ConfigServiceTest extends Thread implements SessionAwareable
{
	public static void main(String[] args) 
	{
		ConfigServiceTest test = new ConfigServiceTest();
		test.start();
	}
	
	public void run()
	{
		ConfigService configService = Tier1ServiceLocator.getInstance().getConfigService();
		Configuration config;
		try 
		{
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
