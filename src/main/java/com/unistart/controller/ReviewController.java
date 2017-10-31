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
import com.unistart.entities.Review;
import com.unistart.entities.ReviewMajor;
import com.unistart.entities.University;
import com.unistart.entities.customentities.MajorPoint;
import com.unistart.entities.customentities.UniversityPoint;
import com.unistart.error.ErrorNotification;
import com.unistart.services.ReviewMajorService;
import com.unistart.services.interfaces.ReviewMajorUniInterface;
import com.unistart.services.interfaces.ReviewServiceInterface;

@RestController
@RequestMapping(UrlConstant.REVIEW)
public class ReviewController {
	
	@Autowired
	private ReviewServiceInterface reviewService;
  
	@Autowired
	private ReviewMajorUniInterface reviewMajorService;
  private ErrorNotification error;
	
	private List<Review> listAllReview;

	@RequestMapping(value = UrlConstant.STAR_POINT, method = RequestMethod.GET)
	public ResponseEntity<?> getStarPoint(@RequestParam(value = "universityId") int universityId) {
		UniversityPoint universityPoint = reviewService.getPointById(universityId);
		if(universityPoint != null){
			return new ResponseEntity<UniversityPoint> (universityPoint, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES007);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = UrlConstant.SAVE_REVIEW, method = RequestMethod.POST)
	public ResponseEntity<?> saveReview(@RequestBody Review review) {
		int universityId = review.getUniversity().getId();
		int userId = review.getUsers().getId();
		String title = review.getTitle();
		String description = review.getDescription();
		int starTeaching = review.getStarTeaching();
		int starFacilities = review.getStarFacilities();
		int starCare = review.getStarCare();
		int starSocieties = review.getStarSocieties();
		int starCareer = review.getStarCareer();
		boolean isRecomment = review.getIsRecomment();
		boolean status = review.getStatus();

		boolean isSuccess = reviewService.saveReview(universityId, userId, title, description, starTeaching, starFacilities, starCare, starSocieties, starCareer, isRecomment, status);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES005);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = UrlConstant.SHOW_REVIEW, method = RequestMethod.GET)
	public ResponseEntity<?> listReviewOfUniveristy(@RequestParam(value = "universityId") int universityId){
		listAllReview = reviewService.listReviewOfUniversity(universityId);
		if (listAllReview != null){
			return new ResponseEntity<List<Review>>(listAllReview, HttpStatus.OK);
		}else {
			error = new ErrorNotification(ErrorConstant.MES007);
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
			error = new ErrorNotification(ErrorConstant.MES007);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value = UrlConstant.NEED_ACCEPT_REVIEW, method = RequestMethod.GET)
	public ResponseEntity<?> listAllNeedAcceptReview(){
		List<Review> listAllReview = reviewService.listAllNeedAcceptReview();
		if(listAllReview != null){
			return new ResponseEntity<List<Review>>(listAllReview, HttpStatus.OK);
		}else {
			error = new ErrorNotification(ErrorConstant.MES007);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
		
	}

	@RequestMapping(value = UrlConstant.NUMBER_NEED_ACCEPT_REVIEW, method = RequestMethod.GET)
	public ResponseEntity<?> getNumberReviewNeedAccept(){
		int numberOfReview = reviewService.numberOfReview();
		return new ResponseEntity<Integer>(numberOfReview, HttpStatus.OK);	
	}
	
	@RequestMapping(value = UrlConstant.SAVE_REVIEW_MAJOR_UNI, method = RequestMethod.POST)
	public ResponseEntity<?> saveReviewMajorUni(@RequestBody ReviewMajor review) {
		int majorUniId = review.getMajorUniversity().getId();
		int userId = review.getUsers().getId();
		int starTeaching = review.getStarTeaching();
		int starCareer = review.getStarCareer();
		boolean isRecomment = review.getIsRecomment();
		boolean isSuccess = reviewMajorService.saveReviewMajorUni(majorUniId, userId, starTeaching, starCareer, isRecomment);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES012);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = UrlConstant.STAR_REIVEW_MAJOR, method = RequestMethod.GET)
	public ResponseEntity<?> getStarReviewMajorUni(@RequestParam(value = "majorUniId") int majorUniId) {
		MajorPoint majorPoint = reviewMajorService.getPointById(majorUniId);
		if(majorPoint != null){
			return new ResponseEntity<MajorPoint> (majorPoint, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES007);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = UrlConstant.CHECK_REVIEWED_UNI, method = RequestMethod.POST)
	public ResponseEntity<?> checkReviewUniversity(@RequestBody Review review) {
		int universityId = review.getUniversity().getId();
		int userId = review.getUsers().getId();
		boolean isReviwed = reviewService.checkReviewedUni(universityId, userId);
		return new ResponseEntity<Boolean> (isReviwed, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.CHECK_REVIEWED_UNI_MAJOR, method = RequestMethod.POST)
	public ResponseEntity<?> checkReviewUniversityMajor(@RequestBody ReviewMajor review) {
		int majorUniId = review.getMajorUniversity().getId();
		int userId = review.getUsers().getId();
		boolean isReviwed = reviewMajorService.checkReviewUniMajor(majorUniId, userId);
		return new ResponseEntity<Boolean> (isReviwed, HttpStatus.OK);
	}
	
}
