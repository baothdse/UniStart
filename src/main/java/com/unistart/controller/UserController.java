package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.ParamConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.UserServiceInterface;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceInterface userService;
	
	@RequestMapping(value = UrlConstant.REGISTER, method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestParam(ParamConstant.USERNAME) String username,
									@RequestParam(ParamConstant.PASSWORD) String password,
									@RequestParam(ParamConstant.EMAIL) String email) {
		boolean isSuccess = userService.register(username, password, email);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			ErrorNotification error = new ErrorNotification(ErrorConstant.ERR001, ErrorConstant.MES001);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.OK);
		}
	}
}
