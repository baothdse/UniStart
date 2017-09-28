package com.unistart.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Location;
import com.unistart.entities.University;
import com.unistart.repositories.LocationRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.UniversityServiceInterface;

@Service
@Transactional
public class UniversityService implements UniversityServiceInterface {

	@Autowired
	private UniversityRepository universityRepo;

	@Autowired
	private LocationRepository locationRepo;
	
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
	
	@Override
	public List<University> listAllUniversityName(){
		listUniversity = universityRepo.showByUniversityName();
		return listUniversity;
	}
	@Override
	public List<University> findUniversity(int majorId, int universityId, int locationId) {
		// TODO Auto-generated method stub
		if (universityId == 0 && locationId == 0 && majorId != 0) {
			listUniversity = universityRepo.findByMajor(majorId);
		} else if (majorId == 0 && locationId == 0 && universityId != 0){
			university = universityRepo.findById(universityId);
			listUniversity = new ArrayList<University>();
			listUniversity.add(university);
		} else if (majorId == 0 && universityId == 0 && locationId != 0) {
			listUniversity = universityRepo.findByLocation(locationId);
		} else if (majorId != 0 && universityId == 0 && locationId !=0) {
			listUniversity = universityRepo.findByLocationAndMajor(majorId, locationId);
		} else if (majorId != 0 && universityId != 0 && locationId == 0) {
			university = universityRepo.findByMajorAndUniversity(majorId, universityId);
			listUniversity = new ArrayList<University>();
			System.out.println(majorId);
			System.out.println(universityId);
			System.out.println(locationId);
			listUniversity.add(university); 
		} else if (majorId == 0 && universityId != 0 && locationId != 0) {
			university = universityRepo.findByLocationAndId(locationId, universityId);
			listUniversity = new ArrayList<University>();
			listUniversity.add(university); 
		} else {
			university = universityRepo.findBy(majorId, universityId, locationId);
			listUniversity = new ArrayList<University>();	
			listUniversity.add(university);
		}
		
		return listUniversity;
	}
	@Override
	public University getUniversityByCode(String code) {	
		return universityRepo.findByCode(code);
	}
	
	@Override
	public boolean addLocation(int locationID,int uniId) {
		universityRepo.addLocation(locationRepo.findById(locationID), uniId);
		return true;
	}


}
