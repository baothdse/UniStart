package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{


	@Query("SELECT r FROM Review r WHERE r.isActive = true and r.status = true and r.university.id = ?1")
	public List<Review> showReviewByUniversityId(int universityId);
	
	@Query("select r from Review r where r.id = ?1")
	public Review findById(int id);

}
