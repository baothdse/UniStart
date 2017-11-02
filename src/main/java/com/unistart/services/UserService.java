package com.unistart.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Provider;
import com.unistart.entities.Role;
import com.unistart.entities.Users;
import com.unistart.entities.customentities.LoginUserInfo;
import com.unistart.repositories.RoleRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.ProviderServiceInterface;
import com.unistart.services.interfaces.UserServiceInterface;

@Service
@Transactional
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private ProviderServiceInterface providerService;
	
	private Users user;

	@Override
	public boolean register(String username, String password, String email) {
		// TODO Auto-generated method stub
		user = userRepository.findByUsername(username);
		if (user == null) {
			user = new Users();
			user.setUsername(username);
			String encodedPassword = bcrypt.encode(password);
			Role role = roleRepository.findById(1);
			user.setPassword(encodedPassword);
			user.setEmail(email);
			user.setIsActive(true);
			user.setRole(role);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public LoginUserInfo checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		user = userRepository.checkLogin(username);
		if (user != null && bcrypt.matches(password, user.getPassword())) {
			LoginUserInfo loginUserInfo = new LoginUserInfo();
			loginUserInfo.setUserId(user.getId());
			loginUserInfo.setUsername(user.getUsername());
			loginUserInfo.setName(user.getName());
			loginUserInfo.setRole(user.getRole());
			loginUserInfo.setEmail(user.getEmail());
			loginUserInfo.setImage(user.getImage());
			return loginUserInfo;
		}
		return null;
	}

	@Override
	public boolean checkLoginThirdParty(String email, String image, String name, String providerId, String providerName) {
		user = userRepository.findByEmail(email);
		if (user == null) {
			user = new Users();
			user.setUsername(email);
			user.setEmail(email);
			user.setImage(image);
			user.setName(name);
			user.setPassword("12345678");
			user.setIsActive(true);
			user.setRole(roleRepository.findById(1));
			providerService.addProvider(providerId, providerName, user);
			
		}
		return true;
	}

	@Override
	public LoginUserInfo get3rdPartyInfo(String email) {
		// TODO Auto-generated method stub
		Users user = userRepository.findByUsername(email);
		LoginUserInfo userInfo = new LoginUserInfo(user.getId(), user.getUsername(), 
				user.getName(), user.getRole(), user.getImage(), user.getEmail());
		return userInfo;
	}

	@Override
	public boolean changeProfile(String name, String email, String image, String password, int userId) {
		user = userRepository.findById(userId);
		if(user != null){
			String encodedPassword = bcrypt.encode(password);
			userRepository.updateProfile(name, email, encodedPassword, image, userId);
			return true;
		}
		return false;
	}

	@Override
	public Users getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

}
