package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;

@Repository
public interface MajorUniRepository extends JpaRepository<MajorUniversity, Integer>{
	MajorUniversity findById(int id);

	List<MajorUniversity> findByUniversity(University university);

	@Query("select m from MajorUniversity m where m.major.id = ?1 and m.university.id = ?2")
	MajorUniversity findByMajorIdAndUniId(int majorId, int uniId);

	@Modifying
	@Query("update MajorUniversity u set u.isActive = ?2 where u.id = ?1")
	void changeIsActive(int id, boolean isActive);
	
//	@Query("select m from MajorUniversity m where m.university.id = ?1")
//	List<MajorUniversity> findByUniId(int uniId);
	
	@Modifying
	@Query("update MajorUniversity u set u.numberOfYear = ?2, u.description = ?3, u.requirement = ?4, u.prospects = ?5 "
			+ "where u.id = ?1")
	void updateMajorUni(int id, double year, String des, String requirement, String pros);
}
