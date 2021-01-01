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
 
 
 
package org.piangles.backbone.services.crypto;

import org.piangles.backbone.services.Locator;

import org.piangles.backbone.services.crypto.CryptoException;
import org.piangles.backbone.services.crypto.CryptoService;

public class CryptoServiceTest 
{
	private static final String cipherAuthorizationId = "a036640c-13eb-4b99-9faf-2ecf9ed9e97d";
	
	public static void main(String[] args) 
	{
		CryptoService cryptoService = Locator.getInstance().getCryptoService();
		try 
		{
			String decryptedValue = cryptoService.decrypt("kj5IPCk6oWkmxezE9yWh3Q==");
			System.out.println(decryptedValue);
		} 
		catch (CryptoException e) 
		{
			e.printStackTrace();
		}
		// ServiceLocator.getInstance().destroy();
		System.exit(0);
	}
}
