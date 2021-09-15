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
 
 
 
package org.piangles.backbone.services;

import org.piangles.backbone.services.auth.AuthenticationService;
import org.piangles.backbone.services.config.ConfigService;
import org.piangles.backbone.services.crypto.CryptoService;
import org.piangles.backbone.services.email.EMailService;
import org.piangles.backbone.services.geo.GeoLocationService;
import org.piangles.backbone.services.id.IdService;
import org.piangles.backbone.services.instrument.InstrumentationService;
import org.piangles.backbone.services.logging.LoggingService;
import org.piangles.backbone.services.msg.MessagingService;
import org.piangles.backbone.services.prefs.UserPreferencesService;
import org.piangles.backbone.services.profile.UserProfileService;
import org.piangles.backbone.services.session.SessionManagementService;
import org.piangles.backbone.services.suggest.AutoSuggestionService;
import org.piangles.backbone.services.test.FeaturesTestService;
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

	public FeaturesTestService getFeaturesTestService()
	{
		return (FeaturesTestService) createProxy(FeaturesTestService.NAME, FeaturesTestService.class);
	}
	
/*
 * Tier1 Configuration
 */
	public InstrumentationService getInstrumentationService()
	{
		return (InstrumentationService) createProxy(InstrumentationService.NAME, InstrumentationService.class);
	}

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

	public UserPreferencesService getUserPreferencesService()
	{
		return (UserPreferencesService) createProxy(UserPreferencesService.NAME, UserPreferencesService.class);
	}

	public UserProfileService getUserProfileService()
	{
		return (UserProfileService) createProxy(UserProfileService.NAME, UserProfileService.class);
	}
	
	public EMailService getEMailService()
	{
		return (EMailService) createProxy(EMailService.NAME, EMailService.class);
	}
	
	public AutoSuggestionService getAutoSuggestionService()
	{
		return (AutoSuggestionService) createProxy(AutoSuggestionService.NAME, AutoSuggestionService.class);
	}
	
	public GeoLocationService getGeoLocationService()
	{
		return (GeoLocationService) createProxy(GeoLocationService.NAME, GeoLocationService.class);
	}
}
