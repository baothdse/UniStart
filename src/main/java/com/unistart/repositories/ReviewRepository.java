package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	@Query("select r from Review r where r.id = ?1")
	public Review findById(int id);
}
