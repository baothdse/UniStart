package com.unistart.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Location;
import com.unistart.entities.TrainSystem;
import com.unistart.entities.University;
import com.unistart.repositories.LocationRepository;
import com.unistart.repositories.TrainRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.UniversityServiceInterface;

@Service
@Transactional
public class UniversityService implements UniversityServiceInterface {

	@Autowired
	private UniversityRepository universityRepo;

	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private TrainRepository trainRepo;
	private University university;

	public List<University> listAllUniversity(){
		List<University> listUniversity = universityRepo.findAll();
		return listUniversity;
	}
	@Override
	public boolean addUniversity(String code, String name, String email, String phone, String logo,
			String image,int priority, String description,int trainSystem) {
		// TODO Auto-generated method stub
		University university = universityRepo.findByCode(code);
		TrainSystem train = trainRepo.findById(trainSystem);
		if (university == null) {
			boolean isActive = true;
			university = new University(code, name, email, phone, logo, image, priority, description, isActive);
			university.setTrainSystem(train);
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
	public University getUniversityShort(int id) {
		// TODO Auto-generated method stub
		return universityRepo.findWithShortData(id);
	}
	
 	@Override
 	public List<University> listAllUniversityName(){
 		List<University> listUniversity = universityRepo.showByUniversityName();
 		return listUniversity;
 	}
	@Override
	public List<University> findUniversity(int majorId, int universityId, int locationId) {
		// TODO Auto-generated method stub
		List<University> listUniversity;
		if (universityId == 0 && locationId == 0 && majorId != 0) {
			listUniversity = universityRepo.findByMajor(majorId);
		} else if (majorId == 0 && locationId == 0 && universityId != 0){
			university = universityRepo.findByUniId(universityId);
			listUniversity = new ArrayList<University>();
			listUniversity.add(university);
		} else if (majorId == 0 && universityId == 0 && locationId != 0) {
			listUniversity = universityRepo.findByLocation(locationId);
		} else if (majorId != 0 && universityId == 0 && locationId !=0) {
			listUniversity = universityRepo.findByLocationAndMajor(majorId, locationId);
		} else if (majorId != 0 && universityId != 0 && locationId == 0) {
			university = universityRepo.findByMajorAndUniversity(majorId, universityId);
			listUniversity = new ArrayList<University>();
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
	public List<University> findUniversityByMajorId(int majorId) {
		List<University> listUniversity;
		listUniversity = universityRepo.findByMajor(majorId);
		return listUniversity;
	}
	
	@Override
	public List<University> getListId() {
		// TODO Auto-generated method stub
		List<University> listUniversity = universityRepo.getListId();
		return listUniversity;
  }

	public University getUniversityByCode(String code) {	
		return universityRepo.findByCode(code);
	}
	
	@Override
	public boolean addLocation(int locationID,int uniId) {
		universityRepo.addLocation(locationRepo.findById(locationID), uniId);
		return true;
	}
	@Override
	public boolean updateUniversity(int id, String code, String name, String email, String phone, String logo, String image, int priority,
			String description, int trainSystem) {
		University university = universityRepo.findById(id);
		if (university != null) {
			universityRepo.updateUniversity(code, name, email, phone, logo, image, priority, description, trainSystem, id);
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteUniversity(int id) {
		universityRepo.changeIsActive(id);
		return true;
	}
	@Override
	public List<University> getUniByLocationMajor(int locationId, int majorId) {
		List<University> listUniversity;
		listUniversity = universityRepo.getByLocationMajor(locationId, majorId);
    return listUniversity;
  }
  @Override
  public List<University> getUniByMajorId(int majorId) {
		List<University> listUniversity;
		listUniversity = universityRepo.findByMajorId(majorId);
		return listUniversity;
	}
  @Override
	public List<University> getUniByLocationId(int locationId) {
		List<University> listUniversity;
		listUniversity = universityRepo.getUniByLocationId(locationId);
		return listUniversity;
	}

}
