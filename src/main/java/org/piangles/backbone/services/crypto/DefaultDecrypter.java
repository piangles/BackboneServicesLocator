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
