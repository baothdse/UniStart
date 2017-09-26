package com.unistart.services.interfaces;

import com.unistart.entities.customentities.LoginUserInfo;

public interface UserServiceInterface {
	boolean register(String username, String password, String email);
	LoginUserInfo checkLogin(String username, String password);
	
	boolean checkLoginThirdParty(String email, String image, String name, String providerId, String providerName);
	
	LoginUserInfo get3rdPartyInfo(String email);

}
