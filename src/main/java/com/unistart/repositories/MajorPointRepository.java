package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unistart.entities.customentities.MajorPoint;
import com.unistart.entities.customentities.UniversityPoint;

public interface MajorPointRepository extends JpaRepository<MajorPoint, Integer>{

	MajorPoint findById(int id);
	@Query("select new com.unistart.entities.customentities.MajorPoint(a.id,a.majorUniversity.id,a.starTeaching,a.starCareer,"
			+ "a.totalReview, a.recommentPoint) from MajorPoint a where a.majorUniversity.id = ?1")
	MajorPoint findByMajorUniId(int majorUniId);
	
}
