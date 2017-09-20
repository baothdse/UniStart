package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unistart.entities.University;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.UniversityServiceInterface;

public class UniversityService implements UniversityServiceInterface {

	@Autowired
	private UniversityRepository universityRepository;
	
	@Override
	public List<University> showUniversity(){
		
		return universityRepository.findbyUniversityName();
		
	}
}
