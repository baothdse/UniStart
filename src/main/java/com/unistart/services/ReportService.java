package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Report;
import com.unistart.entities.Vote;
import com.unistart.repositories.ReportRespository;
import com.unistart.services.interfaces.QAInterface;
import com.unistart.services.interfaces.ReportInterface;
import com.unistart.services.interfaces.UserServiceInterface;
import com.unistart.services.interfaces.VoteInterface;

@Service
@Transactional
public class ReportService implements ReportInterface{

	@Autowired
	private QAInterface qaService;
	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private ReportRespository reportRepo;
	@Override
	public Report getReportByAnswerAndUser(int qaId, int userId) {
		// TODO Auto-generated method stub
		return reportRepo.findByUserAndAnswer(userId, qaId);
	}

	@Override
	public boolean saveReport(int qaId, int userId) {
		// TODO Auto-generated method stub
		Report report = new Report();
		if (getReportByAnswerAndUser(qaId, userId) == null) {
			report.setQuestionAnswer(qaService.getQaByQaId(qaId));
			report.setUser(userService.getUserById(userId));
			qaService.updateTotalReport(qaId);
			reportRepo.save(report);
			return true;
		}
		return false;
	}

}
