package com.unistart.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.QuestionAnswer;

@Repository
public interface QARepository extends JpaRepository <QuestionAnswer, Integer>{
	@Query("select new com.unistart.entities.QuestionAnswer(qa.count) from QuestionAnswer qa"
			+ " where id = ?1")
	QuestionAnswer getCountByQaId(int id);
	
	@Modifying
	@Query("update QuestionAnswer qa set qa.count = ?1 where id = ?2")
	void updateCount(int count, int id);
	
	QuestionAnswer findById(int id);
	
	@Query("select qa from QuestionAnswer qa where qa.parentId = ?1 and qa.type = ?2 and qa.isActive = true")
	List<QuestionAnswer> findByParentId(int parentId, int type);
	
	@Query("select qa from QuestionAnswer qa where qa.type = 1 and qa.isActive = true")
	List<QuestionAnswer> findAllQuestion();
	
	@Query("select qa from QuestionAnswer qa where qa.users.id = ?1 and qa.type = 1 and qa.isActive = true")
	List<QuestionAnswer> findAllQuestionByUserId(int userId);
	
	@Modifying
	@Query("update QuestionAnswer qa set qa.vote = ?1 where id = ?2")
	void setTotalVote(int vote, int qaId);
	
	@Query("select count(*) from QuestionAnswer qa where qa.parentId = ?1")
	int getTotalAnswerOfQuestion(int questionId);
	
	@Modifying
	@Query("update QuestionAnswer qa set qa.title = ?1, qa.content = ?2, qa.lastUpdatedTime = ?3 "
			+ "where qa.id = ?4 and qa.users.id = ?5")
	void updateQa(String title, String contents, Date lastUpdatedTime, int qaId, int userId);
	
	@Query("select qa from QuestionAnswer qa where qa.id = ?1 and qa.users.id = ?2 and qa.isActive = true")
	QuestionAnswer findByIdAndUserId(int qaId, int userId);
}
