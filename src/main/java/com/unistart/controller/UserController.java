package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Users;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.UserServiceInterface;

@RestController
@RequestMapping(value = UrlConstant.USER,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	
	@Autowired
	private UserServiceInterface userService;
	
	private ErrorNotification error;
	
	@RequestMapping(value = UrlConstant.REGISTER, method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody Users user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		boolean isSuccess = userService.register(username, password, email);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES001);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.OK);

		}
	}
	
	@RequestMapping(value = UrlConstant.CHECK_LOGIN, method = RequestMethod.POST)
	public ResponseEntity<?> checkLogin(@RequestBody Users u) {
		String username = u.getUsername();
		String password = u.getPassword();
		Users user = userService.checkLogin(username, password);
		if (user != null) {
			return new ResponseEntity<Users> (user, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES002);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.OK);
		}
	}
}
