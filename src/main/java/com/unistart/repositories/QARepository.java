package com.unistart.repositories;

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
	
	@Query("select qa from QuestionAnswer qa where qa.type = 1 and qa.isActive = true order by qa.createdDateTime desc")
	List<QuestionAnswer> findAllQuestion();
	
	@Query("select qa from QuestionAnswer qa where qa.users.id = ?1 and qa.type = 1 and qa.isActive = true order by qa.createdDateTime desc")
	List<QuestionAnswer> findAllQuestionByUserId(int userId);
	
	@Modifying
	@Query("update QuestionAnswer qa set qa.isActive = false where qa.id = ?1")
	void changeIsActive(int id);
}
