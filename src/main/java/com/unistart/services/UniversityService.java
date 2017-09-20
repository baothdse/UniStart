package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.University;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.UniversityServiceInterface;

@Service
@Transactional
public class UniversityService implements UniversityServiceInterface {

	@Autowired
	private UniversityRepository universityRepo;
	
	private University university;

	@Override
	public boolean addUniversity(String code, String name, String email, String phone, String logo,
			String image, String description) {
		// TODO Auto-generated method stub
		university = universityRepo.findByCode(code);
		if (university == null) {
			boolean isActive = true;
			university = new University(code, name, email, phone, logo, image, description, isActive);
			universityRepo.save(university);
			return true;
		}
		return false;
	}
}
