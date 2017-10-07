package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	//Select count starXXXX
	@Query("select count (r) from Review r where r.starCare != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	int countStarCare(int universityId);
	@Query("select count (r) from Review r where r.starFacilities != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	int countStarFacilites (int universityId);
	@Query("select count (r) from Review r where r.starSocieties != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	int countStarSocieties(int universityId);
	@Query("select count (r) from Review r where r.starTeaching != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	int countStarTeaching(int universityId);
	@Query("select count (r) from Review r where r.starCareer != null and r.university.id = ?1 and r.isActive = true "
			+ "and r.status= true")
	int countStarCareer(int universityId);
	@Query("select count (r) from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	int countReview(int universityId);
	
	
	//select starXXXX point
	@Query("select r.starCare from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	List<Integer> getStarCare(int universityId);
	@Query("select r.starFacilities from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	List<Integer> getStarFacilities(int universityId);
	@Query("select r.starSocieties from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	List<Integer> getStarSocieties(int universityId);
	@Query("select r.starTeaching from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	List<Integer> getStarTeaching(int universityId);
	@Query("select r.starCareer from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	List<Integer> getStarCareer(int universityId);
	
	@Query("select count (r) from Review r where r.isRecomment = true and r.university.id = ?1 and r.isActive = true and r.status= true")
	int countRecommended(int universityId);
	@Query("select count (r) from Review r where r.university.id = ?1 and r.isActive = true and r.status= true")
	int countTotalRecomment(int universityId);

	@Query("SELECT r FROM Review r WHERE r.isActive = true and r.status = true and r.university.id = ?1")
	public List<Review> showReviewByUniversityId(int universityId);
	
	@Query("select r from Review r where r.id = ?1")
	public Review findById(int id);
  
	@Query("select r from Review r where r.status = 0 and r.isActive = 1")
	public List<Review> findNeedAcceptReview();

}
