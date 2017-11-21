package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Report;
import com.unistart.entities.Vote;

@Repository
public interface ReportRespository extends JpaRepository<Report, Integer>{
	@Query("select v from Report v where v.user.id = ?1 and v.questionAnswer.id = ?2")
	Report findByUserAndAnswer(int userId, int qaId);
	@Query("select count(*) from Report v where v.questionAnswer.id = ?1")
	int getTotalReportOfAnswer(int qaId);
	Report findById(int id);
}
