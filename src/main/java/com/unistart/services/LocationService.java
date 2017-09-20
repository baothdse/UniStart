package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Location;
import com.unistart.repositories.LocationRepository;
import com.unistart.services.interfaces.LocationServiceInterface;

@Service
@Transactional
public class LocationService implements LocationServiceInterface {
	
	@Autowired
	private LocationRepository locationRepo;

	private List<Location> listLocation;
	@Override
	public List<Location> listAllLocation(){
		listLocation = locationRepo.findByLocationName();
		return listLocation;
	}
}
