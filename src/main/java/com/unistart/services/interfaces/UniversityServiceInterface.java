package com.unistart.services.interfaces;

import com.unistart.entities.Location;
import com.unistart.entities.University;

public interface UniversityServiceInterface {
	
	boolean addUniversity(String code, String name, String email, String phone,
			String logo, String image, String description);
	
	void setLocation(Location location);
	
	University getUniversityById(int id);
}
