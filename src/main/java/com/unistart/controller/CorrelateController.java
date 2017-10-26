package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.customentities.Correlate;
import com.unistart.entities.customentities.Pearson;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.CorrelateServiceInterface;

@RestController
@RequestMapping(UrlConstant.CORRLATE)
public class CorrelateController {
	private ErrorNotification error;
	@Autowired
	private CorrelateServiceInterface correlateService;
	
	@RequestMapping(value = UrlConstant.TOP_CORRLATE_UNI, method = RequestMethod.GET)
	public ResponseEntity<?> getBlock(@RequestParam(value = "universityId") int universityId) {
		List<Pearson> listCorr = correlateService.count(universityId);
		listCorr = correlateService.getTop5Uni(listCorr);
		return new ResponseEntity<List<Pearson>>(listCorr, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/get-nganh", method = RequestMethod.GET)
//	public ResponseEntity<?> getList(@RequestParam(value = "universityId") int universityId) {
//		List<Correlate> listCorr = correlateService.getTop10Uni(universityId);
//		return new ResponseEntity<List<Correlate>>(listCorr, HttpStatus.OK);
//	}
}
