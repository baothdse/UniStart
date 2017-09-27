package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unistart.entities.Mbtiresult;

public interface MBTIResultRepository extends JpaRepository<Mbtiresult, Integer>{

	Mbtiresult findById(int id);
}
