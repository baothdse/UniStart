package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unistart.entities.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
	University findByCode(String code);
}
