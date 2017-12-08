package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.customentities.UniversityPoint;

@Repository
public interface UniversityPointRepository extends JpaRepository<UniversityPoint, Integer> {
	
	UniversityPoint findById(int id);
	@Query("select new com.unistart.entities.customentities.UniversityPoint(a.id,a.university.id,a.starCare,a.starTeaching,a.starSocieties,a.starFacilities,"
			+ "a.starCareer,a.recommentPoint,a.totalReview) from UniversityPoint a where a.university.id = ?1")
	UniversityPoint findByUniversityId(int universityId);
}
