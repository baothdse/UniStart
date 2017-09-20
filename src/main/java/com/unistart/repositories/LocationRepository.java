package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{
	Location findById(int id);
}
