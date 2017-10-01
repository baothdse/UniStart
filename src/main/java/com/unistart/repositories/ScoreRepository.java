package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Block;
import com.unistart.entities.ScoreHistory;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreHistory, Integer>{
	
	@Query("select s from ScoreHistory s where s.blockMajorUniversity.id = ?1 and s.year = ?2")
    ScoreHistory findByIdAndYear(int blockUniId, int year);
	
	@Modifying
	@Query("update ScoreHistory s set s.score = ?2 where s.blockMajorUniversity.id = ?1 and s.year = ?3")
    void updatScore(int blockUniId, double score, int year);
}
