package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	//Select count starXXXX
	@Query("select sum(r.starCare) from Review r where r.starCare != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	Integer sumStarCare(int universityId);
	@Query("select sum(r.starFacilities) from Review r where r.starFacilities != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	Integer sumStarFacilites (int universityId);
	@Query("select sum(r.starSocieties) from Review r where r.starSocieties != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	Integer sumStarSocieties(int universityId);
	@Query("select sum(r.starTeaching) from Review r where r.starTeaching != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	Integer sumStarTeaching(int universityId);
	@Query("select sum(r.starCareer) from Review r where r.starCareer != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	Integer sumStarCareer(int universityId);
	@Query("select count(r) from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	int countReview(int universityId);
	
	@Query("select count (r) from Review r where r.isRecomment = true and r.university.id = ?1 and r.isActive = true and r.status= true")
	int countRecommended(int universityId);

	@Query("SELECT new com.unistart.entities.Review(r.id, r.title, r.description, r.starTeaching, r.starFacilities, "
			+ "r.starCare, r.starSocieties, r.starCareer, r.isRecomment, r.status, r.isActive)"
			+ " FROM Review r WHERE r.isActive = true and r.status = true and r.university.id = ?1")
	public List<Review> showReviewByUniversityId(int universityId);
	
	@Query("select r from Review r where r.id = ?1")
	public Review findById(int id);
  
	@Query("SELECT new com.unistart.entities.Review(r.id,r.university, r.title, r.description, r.status, r.isActive)"
			+ "from Review r where r.status = 0 and r.isActive = 1")
	public List<Review> findNeedAcceptReview();

	@Query("select count (r) from Review r where r.isActive = true and r.status= false")
	int numberOfReviewNeedAccept();
	
	@Query("select r from Review r where r.university.id=?1 and r.users.id=?2")
	Review checkReviewed(int universityId, int userId);
}
