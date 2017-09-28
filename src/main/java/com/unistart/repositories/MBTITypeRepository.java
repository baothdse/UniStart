package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unistart.entities.Mbtitype;

public interface MBTITypeRepository extends JpaRepository<Mbtitype, Integer> {

	Mbtitype findByMbtitypeName(String name);
}
