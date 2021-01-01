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

import org.piangles.core.util.abstractions.AbstractDecrypter;

/**
 * This class is bit of a hack. There is no way in Java and it is also bad
 * programming practise to figure out at run time which is the calling Object.
 * So we have to revert back to having a static cipherAuthorizationId. However
 * there are significant advantages of not having each component create a class
 * for just Authorization code. So the hack is since this class is used very rarely
 * so we lock on the Class object and swap the dynamic value to static value.
 */
public final class DefaultDecrypter extends AbstractDecrypter
{
	private static String cipherAuthorizationId = null;
	
	@Override //TODO Figure out a way to use encryptedName is possible
	public String decrypt(String encryptedName, String encryptedValue) throws Exception
	{
		String decryptedData = null;
		synchronized(DefaultDecrypter.class) //Lock on this class and then change the static data
		{
			cipherAuthorizationId = getCipherAuthorizationId();
			decryptedData = Locator.getInstance().getCryptoService().decrypt(encryptedValue);
		}
		return decryptedData;
	}
}
