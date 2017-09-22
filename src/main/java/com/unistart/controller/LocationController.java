package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.LocationServiceInterface;
import com.unistart.entities.Location;
import com.unistart.entities.customentities.LocationUniversity;

@RestController
@RequestMapping(UrlConstant.LOCATION)
public class LocationController {
	@Autowired
	private LocationServiceInterface locationService;
	
	private List<Location> listAllLocation;
	
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
	
	@RequestMapping(value = UrlConstant.SHOW_LOCATION, method = RequestMethod.GET)
	public ResponseEntity<?> listAllLocation(){
		listAllLocation = locationService.listAllLocationName();
		return new ResponseEntity<List<Location>>(listAllLocation, HttpStatus.OK);
	}
}

