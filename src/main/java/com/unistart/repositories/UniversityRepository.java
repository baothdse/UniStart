package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
	University findByCode(String code);
	
	@Query("select new com.unistart.entities.University(u.id, u.code, u.name) "
			+ "from University u")
	List<University> findByUniName();
}
