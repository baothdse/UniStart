package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.BlockMajorUniversity;


@Repository
public interface BlockMajorUniRepository extends JpaRepository<BlockMajorUniversity, Integer>{
   BlockMajorUniversity findById(int id);
   
   @Query("select u from BlockMajorUniversity u where u.block.id = ?1 and u.majorUniversity.id = ?2")
   BlockMajorUniversity findByBlockAndMajor(int blockId, int majorId);
   
   	@Modifying
	@Query("update BlockMajorUniversity u set u.isActive = false where u.id = ?1")
	void changeIsActive(int id);
}
