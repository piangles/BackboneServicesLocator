package org.piangles.backbone.services;

import org.piangles.backbone.services.auth.AuthenticationResponse;
import org.piangles.backbone.services.auth.AuthenticationService;
import org.piangles.backbone.services.auth.Credential;
import org.piangles.backbone.services.profile.BasicUserProfile;
import org.piangles.backbone.services.profile.UserProfileService;
import org.piangles.core.services.remoting.SessionAwareable;
import org.piangles.core.services.remoting.SessionDetails;

public class CreateUser extends Thread implements SessionAwareable
{
	private static String cipherAuthorizationId = "7a948dce-1ebb-4770-b077-f453e60243da";

	private static final String DEFAULT_PASSWORD = "Welcome@1";

	public static void main(String[] args) 
	{
		CreateUser test = new CreateUser();
		test.start();
		try
		{
			test.join();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void run()
	{
		try
		{
			int index = 2;
			String firstName = "Test" + index;
			String lastName = "User";
			String emailId = "testuser" + index + "@piangles.com";
			
			//Create User Profile
			BasicUserProfile profile = new BasicUserProfile(firstName, lastName, emailId);
			UserProfileService service = Locator.getInstance().getUserProfileService();
			String userId = service.createProfile(profile);
			System.out.println("Created Userprofile for " + emailId + " UserId:"+ userId);
			
			//Create Auth Entry
			AuthenticationService authService = Locator.getInstance().getAuthenticationService();
			AuthenticationResponse authResponse = authService.createAuthenticationEntry(userId, new Credential(emailId, DEFAULT_PASSWORD));
			System.out.println("Creation of AuthEntry:" + authResponse.isRequestSuccessful());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public SessionDetails getSessionDetails()
	{
		return new SessionDetails("LoggingService", "TODOSessionId");
	}
}
