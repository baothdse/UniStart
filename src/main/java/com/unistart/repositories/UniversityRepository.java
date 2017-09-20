package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.unistart.entities.University;

public interface UniversityRepository extends JpaRepository<University, Integer>{

	@Query("select new com.unistart.entities.University(u.id, u.name) "
			+ "from University u")

	List<University> findbyUniversityName();
}

