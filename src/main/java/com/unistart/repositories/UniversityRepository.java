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
	University findById(int id);
	University findByCode(String code);

	@Modifying
	@Query("update University u set u.location = ?1")
	void setLocation(Location location);
	
	@Query("select new com.unistart.entities.University(u.id,u.image,u.name,u.priority) "
			+ "from University u")
	List<University> showByLocationName();
	
	@Query("select u from University u where u.location.id = ?1")
	List<University> findByLocation(int locationId);
	
	@Query("select u from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 and u.isActive = true")
	List<University> findByMajor(int majorId);
	
	@Query("select u from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 "
			+ "and u.location.id = ?2 and u.isActive = true")
	List<University> findByLocationAndMajor(int majorId, int locationId);
	
	@Query("select u from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and m.id = ?1 "
			+ "and u.id = ?2 and u.isActive = true")
	University findByMajorAndUniversity(int majorId, int universityId);
	
	@Query("select u from University u where u.location.id = ?1 and u.id = ?2")
	University findByLocationAndId(int locationId, int universityId);
	
	@Query("select u from University u, MajorUniversity mu, Major m "
			+ "where u.id = mu.university.id and m.id = mu.major.id and "
			+ "u.isActive = 'true' and m.id = ?1 and u.id = ?2 and u.location.id = ?3")
	University findBy(int majorId, int universityId, int locationId);
}
