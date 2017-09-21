package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.LocationUniversity;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.LocationServiceInterface;

@RestController
@RequestMapping(UrlConstant.LOCATION)
public class LocationController {
	
	@Autowired
	private LocationServiceInterface locationService;
	
	private ErrorNotification error;
	
	@RequestMapping(value = UrlConstant.CREATE, method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody LocationUniversity locationUniversity) {
		boolean isCreated = locationService.createLocation(locationUniversity.getLocation().getLocationName(), 
				locationUniversity.getLocation().getLocationCode(), locationUniversity.getUniversity().getId()); 
		System.out.println(locationUniversity.getLocation().getLocationName());
		System.out.println(locationUniversity.getLocation().getLocationCode());
		System.out.println(locationUniversity.getUniversity().getId());
		if (isCreated) {
			return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES004);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
	}
}
