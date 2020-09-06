package com.TBD.backbone.services.config;

import java.util.Properties;

import com.TBD.backbone.services.Locator;
import com.TBD.core.util.abstractions.AbstractConfigProvider;

public class DefaultConfigProvider extends AbstractConfigProvider
{
	public DefaultConfigProvider(String serviceName, String componentId)
	{
		super(serviceName, componentId);	
	}

	@Override
	public Properties getProperties() throws Exception
	{
		return Locator.getInstance().getConfigService().getConfiguration(getComponentId()).getProperties();
	}
}
