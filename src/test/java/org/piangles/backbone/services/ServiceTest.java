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

import org.piangles.backbone.services.config.ConfigService;
import org.piangles.backbone.services.config.Configuration;
import org.piangles.backbone.services.crypto.CryptoService;
import org.piangles.backbone.services.id.IdService;
import org.piangles.backbone.services.profile.BasicUserProfile;
import org.piangles.backbone.services.profile.UserProfileService;
import org.piangles.backbone.services.session.SessionManagementService;
import org.piangles.core.services.remoting.SessionDetails;
import org.piangles.core.test.AbstractServiceTestClient;

public class ServiceTest extends AbstractServiceTestClient
{
	private static String cipherAuthorizationId = AbstractServiceTestClient.cipherAuthorizationId;

	public static void main(String[] args) 
	{
		new ServiceTest().start();
	}
	
	@Override
	public void runImpl() throws Exception
	{
		session();
	}
	
	private void encrypt() throws Exception
	{
		CryptoService crypto = Locator.getInstance().getCryptoService();
		System.out.println("Encrypted:" + crypto.encrypt(""));
		System.out.println("Decrypted[" + crypto.decrypt("w44ou8sw5qvLbj6VV7plrg==") + "]");
	}

	private void userProfileGet() throws Exception
	{
		UserProfileService service = Locator.getInstance().getUserProfileService();
		System.out.println(service.getProfile("08f9c02d").getEMailId());
	}
	
	private void userProfileCreate() throws Exception
	{
		BasicUserProfile profile = new BasicUserProfile("Test", "User", "testuser@piangles.org", "123-456-7890");
		UserProfileService service = Locator.getInstance().getUserProfileService();
		String id = service.createProfile(profile);
		System.out.println("UserId: " + id);
	}
	
	private void id() throws Exception
	{
		IdService service = Locator.getInstance().getIdService();
		System.out.println(service.getIdentifier("Test"));
	}

	private void config() throws Exception
	{
		ConfigService configService = Locator.getInstance().getConfigService();
		Configuration config;

		System.out.println("Calling getConfiguration...");
		config = configService.getConfiguration("14fe64ea-d15a-4c8b-af2f-f2c7efe1943b");
		System.out.println(config.getProperties());

		// ServiceLocator.getInstance().destroy();
	}
	
	private void session() throws Exception
	{
		SessionManagementService ss = Locator.getInstance().getSessionManagementService();
		System.out.println("Calling session management service....");
		boolean validty = ss.isValid("SomeUser", "SessionId");
		System.out.println("Response for isValid: " + validty);
	}
}
