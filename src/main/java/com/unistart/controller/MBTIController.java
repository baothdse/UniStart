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
import com.unistart.entities.Mbtiquestion;
import com.unistart.entities.Mbtitype;
import com.unistart.services.interfaces.MBTIServiceInterface;

@RestController
@RequestMapping(UrlConstant.MBTI)
public class MBTIController {

	@Autowired
	private MBTIServiceInterface mbtiService;
	
	private List<Mbtiquestion> listMbtiquestion;
	private Mbtitype MBTIType;
	
	@RequestMapping(value = UrlConstant.SHOW_MBTI_QUESTION, method = RequestMethod.GET)
	public ResponseEntity<?> listAllMbtiquestion(){
		listMbtiquestion = mbtiService.listAllMbtiquestion();
		return new ResponseEntity<List<Mbtiquestion>>(listMbtiquestion, HttpStatus.OK);
	}
	
//	@RequestMapping(value = UrlConstant.GET_MBTI_MAJOR, method = RequestMethod.GET)
//	public ResponseEntity<?> getMbtiMajor(@RequestParam(value = "MBTIid") int MBTIid){
//		MBTIType = mbtiService.getMajorMBTI(MBTIid);
//		return new ResponseEntity<Mbtitype>(MBTIType, HttpStatus.OK);
//	}
}
