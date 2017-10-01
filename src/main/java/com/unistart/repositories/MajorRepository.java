package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer>{

	@Query("select new com.unistart.entities.Major(m.id, m.majorName) "
			+ "from Major m where m.isActive = true")
	List<Major> showByMajorName();
	
	Major findById(int id);
}
