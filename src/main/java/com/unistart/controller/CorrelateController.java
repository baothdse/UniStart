package com.unistart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.MajorMbti;
import com.unistart.entities.customentities.Correlate;
import com.unistart.entities.customentities.LocationMajor;
import com.unistart.entities.customentities.Pearson;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.CorrelateServiceInterface;
import com.unistart.services.interfaces.MBTIServiceInterface;

@RestController
@RequestMapping(UrlConstant.CORRLATE)
public class CorrelateController {
	private ErrorNotification error;
	@Autowired
	private CorrelateServiceInterface correlateService;
	@Autowired
	private MBTIServiceInterface mbtiService;
	@RequestMapping(value = UrlConstant.TOP_CORRLATE_UNI, method = RequestMethod.GET)
	public ResponseEntity<?> getTopUniByUniId(@RequestParam(value = "universityId") int universityId) {
		List<Pearson> listCorr = correlateService.count(universityId);
		return new ResponseEntity<List<Pearson>>(correlateService.getTop5Uni(listCorr), HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.TOP_UNI_MBTI, method = RequestMethod.GET)
	public ResponseEntity<?> getTopUniByMBTI(@RequestParam(value = "mbtiTypeId") int mbtiTypeId) {
		List<MajorMbti> listMajorId =new ArrayList<>( mbtiService.getMBTIType(mbtiTypeId).getMajorMbtis());
		List<Pearson> listCorr = correlateService.getTop5UniMBTI(listMajorId);
		return new ResponseEntity<List<Pearson>>(listCorr, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/get-nganh", method = RequestMethod.GET)
//	public ResponseEntity<?> getList(@RequestParam(value = "universityId") int universityId) {
//		List<Correlate> listCorr = correlateService.getTop10Uni(universityId);
//		return new ResponseEntity<List<Correlate>>(listCorr, HttpStatus.OK);
//	}
}
