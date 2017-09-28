package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {
	Block findById(int id);
}
