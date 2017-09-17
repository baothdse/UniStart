package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Role;
import com.unistart.entities.User;
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
			User user = userRepository.findByUsername(username);
			if (user == null) {
				user = new User();
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
	
}
