package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;
import com.unistart.error.ErrorNotification;
import com.unistart.entities.University;
import com.unistart.services.interfaces.MajorServiceInterface;

@RestController
@RequestMapping(UrlConstant.UNIVERSITY)
public class MajorController {
	
	private ErrorNotification error;
	@Autowired
	private MajorServiceInterface majorService;
	private List<Major> listMajor;
	
	@RequestMapping(value = UrlConstant.SHOWMAJOR, method = RequestMethod.GET)
	public ResponseEntity<?> listAllMajor(){
		listMajor = majorService.listAllMajorName();
		return new ResponseEntity<List<Major>>(listMajor, HttpStatus.OK);
	}
	

	@RequestMapping(value = UrlConstant.SAVE_DETAIL_MAJOR, method = RequestMethod.POST)
	public ResponseEntity<?> saveDetailMajor(@RequestBody MajorUniversity majorUni){
		int id = majorUni.getId();
		Double year = majorUni.getNumberOfYear();
		String requirement = majorUni.getRequirement();
		String des = majorUni.getDescription();
		String pros = majorUni.getProspects();
		boolean isUpdate = majorService.saveMajorUniDetail(id, year, des, requirement, pros);
		if(isUpdate){
			return new ResponseEntity<Boolean>(isUpdate, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES009);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = UrlConstant.GET_BY_GROUP, method = RequestMethod.GET)
	public ResponseEntity<?> getByGroup(@RequestParam(value = "majorId") int majorId ){
		List<University> universities = majorService.getByMajor(majorId);
		return new ResponseEntity<List<University>>(universities, HttpStatus.OK);
	}
	@RequestMapping(value = UrlConstant.GET_ALL_MAJOR_OF_UNIVERSITY, method = RequestMethod.GET)
	public ResponseEntity<?> getByUniversityId(@RequestParam(value = "universityId") int uniId ){
		List<MajorUniversity> major = majorService.getByUniversityId(uniId);
		return new ResponseEntity<List<MajorUniversity>>(major, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.GET_FOR_TAG, method = RequestMethod.GET)
	public ResponseEntity<?> getForTag(@RequestParam(value = "universityId") int uniId ){
		List<MajorUniversity> major = majorService.getForTag(uniId);
		return new ResponseEntity<List<MajorUniversity>>(major, HttpStatus.OK);
	}

}
