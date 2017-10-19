package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unistart.entities.TrainSystem;

public interface TrainRepository extends JpaRepository<TrainSystem, Integer>{
     TrainSystem findById(int id);
}
