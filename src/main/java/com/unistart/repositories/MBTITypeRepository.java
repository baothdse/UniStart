package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unistart.entities.Mbtitype;
import com.unistart.entities.University;

public interface MBTITypeRepository extends JpaRepository<Mbtitype, Integer> {

	Mbtitype findByMbtitypeName(String name);
	
	Mbtitype findById(int id);
}
