package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.Major;
import com.unistart.services.interfaces.MajorServiceInterface;

@RestController
@RequestMapping(UrlConstant.UNIVERSITY)
public class MajorController {

	@Autowired
	private MajorServiceInterface majorService;
	private List<Major> listMajor;
	
	@RequestMapping(value = UrlConstant.SHOWMAJOR, method = RequestMethod.GET)
	public ResponseEntity<?> listAllMajor(){
		listMajor = majorService.listAllMajorName();
		return new ResponseEntity<List<Major>>(listMajor, HttpStatus.OK);
	}
}
