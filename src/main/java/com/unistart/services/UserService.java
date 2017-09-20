package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.LoginUserInfo;
import com.unistart.entities.Role;
import com.unistart.entities.Users;
import com.unistart.repositories.RoleRepository;
import com.unistart.repositories.UserRepository;
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

	@Override
	public boolean register(String username, String password, String email) {
		// TODO Auto-generated method stub
			Users user = userRepository.findByUsername(username);
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
		Users user = userRepository.checkLogin(username);
		if (user != null && bcrypt.matches(password, user.getPassword())) {
			LoginUserInfo loginUserInfo = new LoginUserInfo();
			loginUserInfo.setUsername(user.getUsername());
			loginUserInfo.setName(user.getName());
			loginUserInfo.setRole(roleRepository.findById(1));
			loginUserInfo.setEmail(user.getEmail());
			loginUserInfo.setImage(user.getImage());
			return loginUserInfo;
		}
		return null;
	}
	
}
