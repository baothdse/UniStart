package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	@Query("SELECT r FROM Review r")
	public List<Review> showByReviewId();
}
