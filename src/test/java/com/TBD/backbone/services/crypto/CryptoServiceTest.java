package com.TBD.backbone.services.crypto;

import com.TBD.backbone.services.Locator;

public class CryptoServiceTest 
{
	private static final String cipherAuthorizationId = "a036640c-13eb-4b99-9faf-2ecf9ed9e97d";
	
	public static void main(String[] args) 
	{
		CryptoService cryptoService = Locator.getInstance().getCryptoService();
		try 
		{
			String decryptedValue = cryptoService.decrypt("TdmVTwllpL3hE+HyDz1ScA==");
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
