package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Location;
import com.unistart.entities.University;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.UniversityServiceInterface;

@Service
@Transactional
public class UniversityService implements UniversityServiceInterface {

	@Autowired
	private UniversityRepository universityRepo;

	private University university;
	private List<University> listUniversity;

	public List<University> listAllUniversity(){
		listUniversity = universityRepo.findAll();
		return listUniversity;
	}
	@Override
	public boolean addUniversity(String code, String name, String email, String phone, String logo,
			String image, String description) {
		// TODO Auto-generated method stub
		University university = universityRepo.findByCode(code);
		if (university == null) {
			boolean isActive = true;
			university = new University(code, name, email, phone, logo, image, description, isActive);
			universityRepo.save(university);
			return true;
		}
		return false;
	}

	@Override
	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		universityRepo.setLocation(location);
	}

	@Override
	public University getUniversityById(int id) {
		// TODO Auto-generated method stub
		return universityRepo.findById(id);
	}
	
	public List<University> listAllUniversityName(){
		listUniversity = universityRepo.findByLocationName();
		return listUniversity;
	}

}
