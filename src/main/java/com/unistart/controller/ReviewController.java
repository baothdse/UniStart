package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Review;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.ReviewServiceInterface;

@RestController
@RequestMapping(value = UrlConstant.REVIEW)
public class ReviewController {
	@Autowired
	private ReviewServiceInterface reviewService;
	private ErrorNotification error;
	
	@RequestMapping(value = UrlConstant.SAVE_REVIEW, method = RequestMethod.POST)
	public ResponseEntity<?> saveReview(@RequestBody Review Review) {
		int universityId = Review.getUniversity().getId();
		int userId = Review.getUsers().getId();
		String description = Review.getDescription();
		int starTeaching = Review.getStarTeaching();
		int starFacilities = Review.getStarFacilities();
		int starCare = Review.getStarCare();
		int starSocieties = Review.getStarSocieties();
		int starCareer = Review.getStarCareer();
		boolean isRecomment = Review.getIsRecomment();
		boolean status = Review.getStatus();

		boolean isSuccess = reviewService.saveReview(universityId, userId, description, starTeaching, starFacilities, starCare, starSocieties, starCareer, isRecomment, status);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES005);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value = UrlConstant.CHANGE_REVIEW_STATUS, method = RequestMethod.POST)
	public ResponseEntity<?> changeReviewStatus(@RequestBody Review Review) {
		int id = Review.getId();
		boolean status = Review.getStatus();
		boolean isActive = Review.getIsActive();

		boolean isSuccess = reviewService.changeReviewStatus(id, status, isActive);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES005);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
}
