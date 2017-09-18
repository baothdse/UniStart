package com.unistart.services.interfaces;

import com.unistart.entities.Users;

public interface UserServiceInterface {
	boolean register(String username, String password, String email);
	Users checkLogin(String username, String password); 
}
