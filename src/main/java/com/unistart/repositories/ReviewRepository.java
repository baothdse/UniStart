package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	Review findById(int id);
}
