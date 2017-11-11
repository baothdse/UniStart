package com.unistart.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.QuestionTag;


@Repository
public interface QATagRepository extends JpaRepository<QuestionTag, Integer>{

	@Query("SELECT q FROM QuestionTag q WHERE q.qa.id = ?1")
	List<QuestionTag> findByQuestionId(int questionId);
	
	@Query("select q from QuestionTag q where q.qa.id=?1 and q.tag.id=?2")
	QuestionTag findByqaIdAnduniId(int qaId, int universityId);
	
	@Transactional
	@Modifying
	@Query("delete from QuestionTag q where q.id=?1 ")
	void deleteTag(int id);
	
	@Query("select new com.unistart.entities.QuestionTag(a.id,a.tag) from QuestionTag a where a.qa.id=?1")
	List<QuestionTag> getTagByquestionId(int questionId);
}
