package com.unistart.repositories;

import java.util.List;

import org.springframework.boot.test.autoconfigure.properties.SkipPropertyMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Major;
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
	
	@Query("select m from MajorUniversity m where m.university.id = ?1 and m.isActive = true")
	List<MajorUniversity> findByUniId(int uniId);
	
	@Query("select new com.unistart.entities.MajorUniversity(m.id,m.major) from MajorUniversity m where m.university.id = ?1 and m.isActive = true")
	List<MajorUniversity> findByUniIdShort(int uniId);
	
	@Query("select new com.unistart.entities.MajorUniversity(m.id,m.major,m.isActive) from MajorUniversity m where m.university.id = ?1 and m.isActive = true")
	List<MajorUniversity> findByIdForTag(int uniId);
	
	@Query("select new com.unistart.entities.Major(m.major.id, m.major.majorName) "
			+ "from MajorUniversity m where m.university.id = ?1 and m.isActive = true")
	List<MajorUniversity> getByUniversityId(int uniId);
	
	@Modifying
	@Query("update MajorUniversity u set u.numberOfYear = ?2, u.description = ?3, u.requirement = ?4, u.prospects = ?5 "
			+ "where u.id = ?1")
	void updateMajorUni(int id, double year, String des, String requirement, String pros);

	@Query("select new com.unistart.entities.University(m.university.id, m.university.name, m.university.logo) "
			+ "from MajorUniversity m where m.major.id = ?1")
	List<University> getByMajor(int majorId);

	@Query("select new com.unistart.entities.MajorUniversity(u.id) from MajorUniversity u")
	List<MajorUniversity> getListId();
	
	@Query("select DISTINCT new com.unistart.entities.MajorUniversity(u.university) from MajorUniversity u "
			+ "where u.university.id != ?1")
	List<MajorUniversity> getListUni(int universityId);
}
