package com.unistart.services.interfaces;

import com.unistart.entities.LoginUserInfo;

public interface UserServiceInterface {
	boolean register(String username, String password, String email);
	LoginUserInfo checkLogin(String username, String password); 
}
