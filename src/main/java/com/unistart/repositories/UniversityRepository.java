package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Location;
import com.unistart.entities.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
	University findById(Integer id);
	University findByCode(String code);

	@Modifying
	@Query("update University u set u.location = ?1")
	void setLocation(Location location);
	
	@Modifying
	@Query("update University u set u.location = ?1 where u.id = ?2 ")
	void addLocation(Location location, int id);
	
	@Query("select new com.unistart.entities.University(u.id,u.image,u.name,u.priority) "
			+ "from University u where u.isActive = true")
	List<University> showByUniversityName();

	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u where u.location.id = ?1")
	List<University> findByLocation(int locationId);
	
	@Query("select new com.unistart.entities.University(u.id,u.name) from University u where u.location.id = ?1")
	List<University> getUniByLocationId(int locationId);
	
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.location,u.trainSystem, u.logo) from University u where u.id = ?1")
	University findWithShortData(Integer id);
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 and u.isActive = true")
	List<University> findByMajor(int majorId);
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u where u.id = ?1 and u.isActive = true")
	University findByUniId(int uniId);
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 "
			+ "and u.location.id = ?2 and u.isActive = true")
	List<University> findByLocationAndMajor(int majorId, int locationId);
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 "
			+ "and u.id = ?2 and u.isActive = true")
	University findByMajorAndUniversity(int majorId, int universityId);
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u where u.location.id = ?1 and u.id = ?2")
	University findByLocationAndId(int locationId, int universityId);
	
	@Query("select new com.unistart.entities.University(u.id,u.name,u.logo,u.phone) from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and "
			+ "u.isActive = 'true' and m.id = ?1 and u.id = ?2 and u.location.id = ?3")
	University findBy(int majorId, int universityId, int locationId);
	

	@Query("select new com.unistart.entities.University(u.id) from University u where u.isActive = true")
	List<University> getListId();
  
	@Modifying
	@Query("update University u set u.isActive = false where u.id = ?1")
	void changeIsActive(int id);
	
	@Modifying
	@Query("update University u set u.code = ?1, u.name= ?2, u.email= ?3, u.phone = ?4,"
			+ "u.logo = ?5, u.image = ?6, u.priority = ?7, u.description = ?8, u.trainSystem.id = ?9 where u.id = ?10 ")
	void updateUniversity(String code, String name, String email, String phone,
			String logo, String image,int priority, String description,int trainSystem,int id);
	
	@Query("select new com.unistart.entities.University(u.id,u.name) from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?2 "
			+ "and u.location.id = ?1 and u.isActive = true and mu.isActive = true")
	List<University> getByLocationMajor(int locationId, int majorId);

  @Query("select new com.unistart.entities.University(u.id,u.name) from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 and u.isActive = true and mu.isActive = true")
	List<University> findByMajorId(int majorId);


}
