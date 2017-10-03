package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Block;


@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {
	Block findById(int id);
	Block findByBlockName(String blockName);
	@Query("select new com.unistart.entities.Block(b.id, b.blockName) "
			+ "from Block b where b.isActive = true")
	List<Block> getListOfBlock();
}
