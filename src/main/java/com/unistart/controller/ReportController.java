package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.Report;
import com.unistart.entities.Vote;
import com.unistart.services.interfaces.ReportInterface;

@RestController
@RequestMapping(value = UrlConstant.REPORT)
public class ReportController {

	@Autowired
	private ReportInterface reportService;
	
	@RequestMapping(value = UrlConstant.SAVE, method = RequestMethod.POST)
	public ResponseEntity<?> saveReport(@RequestBody Report report) {
		int qaId = report.getQuestionAnswer().getId();
		int userId = report.getUser().getId();
		boolean isSuccess = reportService.saveReport(qaId, userId);
		if(isSuccess) {
			return new ResponseEntity<String>("Save successful", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Cannot save", HttpStatus.CONFLICT);		
	}
}
