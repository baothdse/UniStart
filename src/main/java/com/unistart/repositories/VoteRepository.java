package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer>{
	@Query("select v from Vote v where v.user.id = ?1 and v.questionAnswer.id = ?2")
	Vote findByUserAndAnswer(int userId, int qaId);
	@Query("select count(*) from Vote v where v.questionAnswer.id = ?1")
	int getTotalVoteOfAnswer(int qaId);
	Vote findById(int id);
}
