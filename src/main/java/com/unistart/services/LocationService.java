package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Location;
import com.unistart.entities.University;
import com.unistart.repositories.LocationRepository;
import com.unistart.services.interfaces.LocationServiceInterface;
import com.unistart.services.interfaces.UniversityServiceInterface;

@Service
@Transactional
public class LocationService implements LocationServiceInterface {
	
	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private UniversityServiceInterface universityService;
	
	private Location location;
	@Override
	public boolean createLocation(String locationName, String locationCode, int universityId) {
		// TODO Auto-generated method stub
		System.out.println(locationName);
		System.out.println(locationCode);
		System.out.println(universityId);
		location = locationRepo.findByLocationCode(locationCode);
		University university = universityService.getUniversityById(universityId);
		if (location == null) {
			location = new Location();
			location.setLocationCode(locationCode);
			location.setLocationName(locationName);
			locationRepo.save(location);
			university.setLocation(location);
			universityService.setLocation(location);
			return true;
		}
		return false;
	}

	private List<Location> listLocation;
	
	@Override
	public List<Location> listAllLocationName(){
		listLocation = locationRepo.showByLocationName();
		return listLocation;
	}
}
