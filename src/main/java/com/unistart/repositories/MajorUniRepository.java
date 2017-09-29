package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;


public interface MajorUniRepository extends JpaRepository<MajorUniversity, Integer>{
      List<MajorUniversity> findByUniversity (University university);
}
