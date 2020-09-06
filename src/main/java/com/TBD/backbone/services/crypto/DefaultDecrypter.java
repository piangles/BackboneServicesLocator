package com.TBD.backbone.services.crypto;

import com.TBD.backbone.services.Locator;
import com.TBD.core.util.abstractions.AbstractDecrypter;

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
	private String instanceOfCipherAuthorizationId = null;
	
	//TODO CHECK IF THIS WILL WORK
	public DefaultDecrypter(String serviceName, String instanceOfCipherAuthorizationId)
	{
		super(serviceName);
		this.instanceOfCipherAuthorizationId = instanceOfCipherAuthorizationId;
	}
	
	@Override
	public String decrypt(String encryptedName, String encryptedValue) throws Exception
	{
		String decryptedData = null;
		synchronized(DefaultDecrypter.class) //Lock on this class and then change the static data
		{
			cipherAuthorizationId = instanceOfCipherAuthorizationId;
			decryptedData = Locator.getInstance().getCryptoService().decrypt(encryptedValue);
		}
		return decryptedData;
	}
}
