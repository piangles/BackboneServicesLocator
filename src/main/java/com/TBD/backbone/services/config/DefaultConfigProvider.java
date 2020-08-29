package com.TBD.backbone.services.config;

import java.util.Properties;

import com.TBD.core.util.abstractions.ConfigProvider;

public class DefaultConfigProvider implements ConfigProvider
{
	private String componentId = null;
			
	public DefaultConfigProvider(String componentId)
	{
		this.componentId = componentId;
	}

	@Override
	public String getComponentId()
	{
		return componentId;
	}

	@Override
	public Properties getProperties() throws Exception
	{
		return Tier1ServiceLocator.getInstance().getConfigService().getConfiguration(componentId).getProperties();
	}

}
