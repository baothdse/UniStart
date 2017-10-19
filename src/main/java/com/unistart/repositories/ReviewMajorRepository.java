package com.unistart.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unistart.entities.ReviewMajor;

@Repository
public interface ReviewMajorRepository extends JpaRepository<ReviewMajor, Integer>{
	//Select count starXXXX
	@Query("select sum(r.starTeaching) from ReviewMajor r where r.starTeaching != null and r.majorUniversity.id = ?1 and r.isActive = true")
	Integer sumStarTeaching(int majorUniId);
	@Query("select sum(r.starCareer) from ReviewMajor r where r.starCareer != null and r.majorUniversity.id = ?1 and r.isActive = true")
	Integer sumStarCareer(int majorUniId);
	@Query("select count (r) from ReviewMajor r where r.majorUniversity.id = ?1 and r.isActive = true")
	int countReview(int universityId);
	@Query("select count (r) from ReviewMajor r where r.isRecomment = true and r.majorUniversity.id = ?1 and r.isActive = true")
	int countRecommended(int universityId);
	
	//select starXXXX point
	@Query("select r.starTeaching from ReviewMajor r where r.majorUniversity.id = ?1 and r.isActive = true")
	List<Integer> getStarTeaching(int majorUniId);
	@Query("select r.starCareer from ReviewMajor r where r.majorUniversity.id = ?1 and r.isActive = true")
	List<Integer> getStarCareer(int majorUniId);
	
	@Query("select r from ReviewMajor r where r.majorUniversity.id = ?1 and r.users.id = ?2")
	ReviewMajor checkReviewed(int majorUniId, int userId);
}
