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

/*
 * Tier1 Configuration
 */
	public SessionManagementService getSessionManagementService()
	{
		return (SessionManagementService) createProxy(SessionManagementService.NAME, SessionManagementService.class);
	}
	
	public ConfigService getConfigService()
	{
		return (ConfigService) createProxy(ConfigService.NAME, ConfigService.class);
	}

	public CryptoService getCryptoService()
	{
		return (CryptoService) createProxy(CryptoService.NAME, CryptoService.class);
	}

/**
 * Rest of the Backbone Services 	
 */
	public LoggingService getLoggingService()
	{
		return (LoggingService) createProxy(LoggingService.NAME, LoggingService.class);
	}

	public IdService getIdService()
	{
		return (IdService) createProxy(IdService.NAME, IdService.class);
	}

	public AuthenticationService getAuthenticationService()
	{
		return (AuthenticationService) createProxy(AuthenticationService.NAME, AuthenticationService.class);
	}
	
	public MessagingService getMessagingService()
	{
		return (MessagingService) createProxy(MessagingService.NAME, MessagingService.class);
	}

	public UserPreferenceService getUserPreferenceService()
	{
		return (UserPreferenceService) createProxy(UserPreferenceService.NAME, UserPreferenceService.class);
	}

	public UserProfileService getUserProfileService()
	{
		return (UserProfileService) createProxy(UserProfileService.NAME, UserProfileService.class);
	}
}
