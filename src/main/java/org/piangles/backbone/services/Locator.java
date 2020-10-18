package org.piangles.backbone.services;

import org.piangles.backbone.services.auth.AuthenticationService;
import org.piangles.backbone.services.config.ConfigService;
import org.piangles.backbone.services.crypto.CryptoService;
import org.piangles.backbone.services.id.IdService;
import org.piangles.backbone.services.logging.LoggingService;
import org.piangles.backbone.services.msg.MessagingService;
import org.piangles.backbone.services.prefs.UserPreferenceService;
import org.piangles.backbone.services.profile.UserProfileService;
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

	public SessionManagementService getSessionManagementService()
	{
		return (SessionManagementService) createProxy("SessionManagementService", SessionManagementService.class);
	}
	
	//Tier1
	public ConfigService getConfigService()
	{
		return (ConfigService) createProxy("ConfigService", ConfigService.class);
	}

	public CryptoService getCryptoService()
	{
		return (CryptoService) createProxy("CryptoService", CryptoService.class);
	}

	public LoggingService getLoggingService()
	{
		return (LoggingService) createProxy("LoggingService", LoggingService.class);
	}

	//Rest of the Services
	public AuthenticationService getAuthenticationService()
	{
		return (AuthenticationService) createProxy("AuthenticationService", AuthenticationService.class);
	}
	
	public IdService getIdService()
	{
		return (IdService) createProxy("IdService", IdService.class);
	}

	public MessagingService getMessagingService()
	{
		return (MessagingService) createProxy("MessagingService", MessagingService.class);
	}

	public UserPreferenceService getUserPreferenceService()
	{
		return (UserPreferenceService) createProxy("UserPreferenceService", UserPreferenceService.class);
	}

	public UserProfileService getUserProfileService()
	{
		return (UserProfileService) createProxy("UserProfileService", UserProfileService.class);
	}
}
