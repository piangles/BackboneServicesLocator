package org.piangles.backbone.services;

import org.piangles.backbone.services.ctrl.ControlChannelService;

import org.piangles.backbone.services.auth.AuthenticationService;
import org.piangles.backbone.services.config.ConfigService;
import org.piangles.backbone.services.crypto.CryptoService;
import org.piangles.backbone.services.id.IdService;
import org.piangles.backbone.services.logging.LoggingService;
import org.piangles.backbone.services.prefs.UserPreferenceService;
import org.piangles.backbone.services.session.SessionManagementService;
import org.piangles.core.services.remoting.locator.AbstractServiceLocator;

public final class Locator extends AbstractServiceLocator
{
	private static Locator self = null;

	private Locator()
	{
		
	}

	public static Locator getInstance()
	{
		synchronized (Locator.class)
		{
			if (self == null)
			{
				self = new Locator();
			}
		}

		return self;
	}

	//Tier1
	public SessionManagementService getSessionManagementService()
	{
		return (SessionManagementService) createProxy("SessionManagementService", SessionManagementService.class);
	}
	
	//Tier2
	public ConfigService getConfigService()
	{
		return (ConfigService) createProxy("ConfigService", ConfigService.class);
	}

	public CryptoService getCryptoService()
	{
		return (CryptoService) createProxy("CryptoService", CryptoService.class);
	}
	
	//Tier3
	public LoggingService getLoggingService()
	{
		return (LoggingService) createProxy("LoggingService", LoggingService.class);
	}

	//Tier4
	public IdService getIdService()
	{
		return (IdService) createProxy("IdService", IdService.class);
	}
	
	//Tier5
	public AuthenticationService getAuthenticationService()
	{
		return (AuthenticationService) createProxy("AuthenticationService", AuthenticationService.class);
	}
	
	public UserPreferenceService getUserPreferenceService()
	{
		return (UserPreferenceService) createProxy("UserPreferenceService", UserPreferenceService.class);
	}
	
	public ControlChannelService getChannelControlService()
	{
		return (ControlChannelService) createProxy("ControlChannelService", ControlChannelService.class);
	}
}
