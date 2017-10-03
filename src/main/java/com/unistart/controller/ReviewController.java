package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.University;
import com.unistart.entities.customentities.UniversityPoint;
import com.unistart.services.interfaces.ReviewServiceInterface;

@RestController
@RequestMapping(UrlConstant.REVIEW)
public class ReviewController {
	
	@Autowired
	private ReviewServiceInterface reviewService;

	@RequestMapping(value = UrlConstant.STAR_POINT, method = RequestMethod.POST)
	public ResponseEntity<?> getStarPoint(@RequestBody University university) {
		int universityId = university.getId();
		UniversityPoint universityPoint = reviewService.getPointById(universityId);
		return new ResponseEntity<UniversityPoint> (universityPoint, HttpStatus.OK);
	}

}
