package com.unistart.services.interfaces;

import com.unistart.entities.Users;
import com.unistart.entities.customentities.LoginUserInfo;

public interface UserServiceInterface {
	boolean register(String username, String password, String email);
	LoginUserInfo checkLogin(String username, String password);
	boolean changeProfile (String name, String email, String image, String password, int userId);
	boolean checkLoginThirdParty(String email, String image, String name, String providerId, String providerName);
	
	LoginUserInfo get3rdPartyInfo(String email);
	Users getUserById(int id);
}
