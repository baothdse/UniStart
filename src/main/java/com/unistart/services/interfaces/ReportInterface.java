package com.unistart.services.interfaces;

import com.unistart.entities.Report;
import com.unistart.entities.Vote;

public interface ReportInterface {
	Report getReportByAnswerAndUser(int qaId, int userId);
	boolean saveReport(int qaId, int userId);
	
}
