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
	
	@Query("select new com.unistart.entities.University(u.id,u.image,u.name) "
			+ "from University u")
	List<University> findByLocationName();
}
