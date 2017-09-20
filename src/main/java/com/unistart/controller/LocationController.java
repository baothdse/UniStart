package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.Location;
import com.unistart.services.interfaces.LocationServiceInterface;

@RestController
@RequestMapping(UrlConstant.LOCATION)
public class LocationController {

	@Autowired
	private LocationServiceInterface loactionService;
	private List<Location> listAllLocation;
	
	@RequestMapping(value = UrlConstant.SHOWLOCATION, method = RequestMethod.GET)
	public ResponseEntity<?> listAllLocation(){
		listAllLocation = loactionService.listAllLocation();
		return new ResponseEntity<List<Location>>(listAllLocation, HttpStatus.OK);
	}
}