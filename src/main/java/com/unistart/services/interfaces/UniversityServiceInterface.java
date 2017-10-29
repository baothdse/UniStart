package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Location;
import com.unistart.entities.University;

public interface UniversityServiceInterface {
	
	boolean addUniversity(String code, String name, String email, String phone,
			String logo, String image,int priority, String description, int trainSystem);
	
	boolean updateUniversity(int id,String code, String name, String email, String phone,
			String logo, String image,int priority, String description, int trainSystem);
	
	boolean addLocation(int locationId, int uniId);
	
	boolean deleteUniversity(int id);
	
	void setLocation(Location location);
	
	University getUniversityById(int id);
	University getUniversityShort(int id);
	
	University getUniversityByCode(String id);
	
	List<University> listAllUniversity();
	
	List<University> listAllUniversityName();
	
	List<University> findUniversity(int majorId, int universityId, int locationId);
	
	List<University> findUniversityByMajorId(int majorId);
	
	List<University> getListId();

	List<University> getUniByLocationMajor(int locationId, int majorId);
	List<University> getUniByMajorId(int majorId);
	List<University> getUniByLocationId(int locationId);

}
