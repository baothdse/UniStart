package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Location;

public interface LocationServiceInterface {
	
	boolean createLocation(String locationName, String locationCode, int universityId);
	List<Location> listAllLocationName();
}
