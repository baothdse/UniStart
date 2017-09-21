package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.University;

public interface UniversityServiceInterface {
	
	boolean addUniversity(String code, String name, String email, String phone,
			String logo, String image, String description);
	List<University> listAllUniversity();
}
