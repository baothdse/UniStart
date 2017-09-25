package com.unistart.services;

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
	public boolean checkLoginThirdParty(String email, String image, String name, int providerId) {
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
			Provider provider = providerService.getById(providerId);
			provider.setUsers(user);
			userRepository.save(user);
			return true;
		}
		return true;
	}

}
