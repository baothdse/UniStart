package com.unistart.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.MajorUniversity;
import com.unistart.entities.Review;
import com.unistart.entities.University;
import com.unistart.entities.Users;
import com.unistart.entities.customentities.MajorPoint;
import com.unistart.entities.customentities.ReviewUniversity;
import com.unistart.entities.customentities.UniversityPoint;
import com.unistart.repositories.ReviewRepository;
import com.unistart.repositories.UniversityPointRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.ReviewServiceInterface;
import com.unistart.services.interfaces.UniversityServiceInterface;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
@Transactional
public class ReviewService implements ReviewServiceInterface {


	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UniversityServiceInterface uniService;
	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private UniversityPointRepository uniPointRepo;

	private University university;
	private Review review;
	private Users user;
	private List<Review> listAllReview = new ArrayList<>();
	private UniversityPoint universityPoint;
	private List<UniversityPoint> listUniversityPoint;
	private List<ReviewUniversity> listAllReviewUni;

	private Double starCare;
	private Double starTeaching;
	private Double starSocieties;
	private Double starFacilities;
	private Double starCareer;
	private Double recommentPoint;
	private int totalReview;

	public double calculateStarTeaching(int universityId) {
		int totalReview = reviewRepo.countReview(universityId);
		if(reviewRepo.sumStarTeaching(universityId) != null && totalReview != 0){
			int totalPoint = reviewRepo.sumStarTeaching(universityId);
			double average =  (double) totalPoint / totalReview;
			return average;
		}
		return -1;
	}

	public double calculateStarFacilities(int universityId) {
		int totalReview = reviewRepo.countReview(universityId);
		if(reviewRepo.sumStarFacilites(universityId) != null && totalReview != 0){
			int totalPoint = reviewRepo.sumStarFacilites(universityId);
			double average =  (double) totalPoint / totalReview;
			return average;
		}
		return -1;
	}

	public double calculateStarSocieties(int universityId) {
		int totalReview = reviewRepo.countReview(universityId);
		if(reviewRepo.sumStarSocieties(universityId) != null && totalReview != 0){
			int totalPoint = reviewRepo.sumStarSocieties(universityId);
			double average =  (double) totalPoint / totalReview;
			return average;
		}
		return -1;
	}

	public double calculateStarCare(int universityId) {
		int totalReview = reviewRepo.countReview(universityId);
		if(reviewRepo.sumStarCare(universityId) != null && totalReview != 0){
			int totalPoint = reviewRepo.sumStarCare(universityId);
			double average =  (double) totalPoint / totalReview;
			return average;
		}
		return -1;
	}

	public double calculateStarCareer(int universityId) {
		int totalReview = reviewRepo.countReview(universityId);
		if(reviewRepo.sumStarCareer(universityId) != null && totalReview != 0){
			int totalPoint = reviewRepo.sumStarCareer(universityId);
			double average =  (double) totalPoint / totalReview;
			return average;
		}
		return -1;
	}
	
	public Double getRecommentPoint() {
		return recommentPoint;
	}

	public void setRecommentPoint(Double recommentPoint) {
		this.recommentPoint = recommentPoint;
	}

	public void calculateRecomment(int universityId) {
		// TODO Auto-generated method stub
		int totalRecomment = reviewRepo.countReview(universityId);
		int trueComment = reviewRepo.countRecommended(universityId);
		if (totalRecomment != 0) {
			double averagePercent =  ((double)(trueComment)/totalRecomment) * 100;
			setRecommentPoint((double)Math.round(averagePercent)); 
		}else {
			setRecommentPoint(null);
		}
	}
	
	public List<UniversityPoint> getListUniversityPoint() {
		return listUniversityPoint;
	}

	public void setListUniversityPoint(List<UniversityPoint> listUniversityPoint) {
		this.listUniversityPoint = listUniversityPoint;
	}

	
	public List<ReviewUniversity> getListAllReviewUni() {
		return listAllReviewUni;
	}

	public void setListAllReviewUni(List<ReviewUniversity> listAllReviewUni) {
		this.listAllReviewUni = listAllReviewUni;
	}

	@Override
//	@PostConstruct
//	@Scheduled(cron = "0 0 2 * * *")
	public void calculateTotalAverage(int universityId) {
		//List<University> listId = uniService.getListId();
//for(int i =0; i<listId.size();i++){
//	starTeaching = calculateStarTeaching(listId.get(i).getId());
//	starFacilities = calculateStarFacilities(listId.get(i).getId());
//	starSocieties = calculateStarSocieties(listId.get(i).getId());
//	starCare = calculateStarCare(listId.get(i).getId());
//	starCareer = calculateStarCareer(listId.get(i).getId());
//	calculateRecomment(listId.get(i).getId());
//	totalReview = reviewRepo.countReview(listId.get(i).getId());
	
	starTeaching = calculateStarTeaching(universityId);
	starFacilities = calculateStarFacilities(universityId);
	starSocieties = calculateStarSocieties(universityId);
	starCare = calculateStarCare(universityId);
	starCareer = calculateStarCareer(universityId);
	calculateRecomment(universityId);
	totalReview = reviewRepo.countReview(universityId);
	UniversityPoint point;
	if (starTeaching != -1 && starFacilities != -1 && starSocieties != -1 && starCare != -1 && starCareer != -1) {
		if (getRecommentPoint() != null) {
			point = uniPointRepo.findByUniversityId(universityId);
			if(point != null){
				point.setUniversity(universityRepo.findById(universityId));
				point.setRecommentPoint(getRecommentPoint());
				point.setStarCare(starCare);
				point.setStarCareer(starCareer);
				point.setStarFacilities(starFacilities);
				point.setStarSocieties(starSocieties);
				point.setStarTeaching(starTeaching);
				point.setTotalReview(totalReview);
				uniPointRepo.save(point);
			}else{
				university = universityRepo.findById(universityId);
				point = new UniversityPoint();
				point.setRecommentPoint(getRecommentPoint());
				point.setStarCare(starCare);
				point.setStarCareer(starCareer);
				point.setStarFacilities(starFacilities);
				point.setStarSocieties(starSocieties);
				point.setStarTeaching(starTeaching);
				point.setTotalReview(totalReview);
				point.setUniversity(university);
				uniPointRepo.save(point);
			}
		}
	}else{
		point = uniPointRepo.findByUniversityId(universityId);
		if(point != null){
			uniPointRepo.delete(point);
		}
	}
}
		
	//}

	@Override
	public UniversityPoint getPointById(int universityId) {
		UniversityPoint uniPoint = uniPointRepo.findByUniversityId(universityId);
//		for (int index = 0; index < listUniversityPoint.size(); index++) {
//			if(universityId == listUniversityPoint.get(index).getUniversity().getId()) {
//				uniPoint = listUniversityPoint.get(index);
//			}
//		}
		return uniPoint;
	}
	
	@Override
	public boolean saveReview(int universityId, int userId,String title, String description, int starTeaching, int starFacilities, int starCare,
			 int starSocieties, int starCareer, boolean isRecomment, boolean status) {
		university = universityRepo.findById(universityId);
		user = userRepo.findById(userId);
		if (university != null && user != null) {
			review = new Review();
			review.setUniversity(university);
			review.setUsers(user);
			review.setTitle(title);
			review.setDescription(description);
			review.setStarTeaching(starTeaching);
			review.setStarFacilities(starFacilities);
			review.setStarCare(starCare);
			review.setStarSocieties(starSocieties);
			review.setStarCareer(starCareer);
			review.setIsRecomment(isRecomment);
			review.setStatus(status);
			review.setIsActive(true);
			reviewRepo.save(review);
			return true;
		}
		return false;
	}

	@Override
	public List<Review> listReviewOfUniversity(int universityId) {
//		for(int i =0; i<listAllReviewUni.size();i++){
//			if(universityId == listAllReviewUni.get(i).getUniversityId()){
//				listAllReview = listAllReviewUni.get(i).getReview();
//			}
//		}
		listAllReview = reviewRepo.showReviewByUniversityId(universityId);
		return listAllReview;
	}
	
	public boolean changeReviewStatus(int id, boolean status, boolean isActive) {	
		review = reviewRepo.findById(id);
		if (review != null){
			boolean isApproved = false;
			if(review.getStatus() == true && review.getIsActive() == true){
				isApproved = true;
			}
			review.setStatus(status);
			review.setIsActive(isActive);
			reviewRepo.save(review);
			if(status==true && isActive==true){
				calculateTotalAverage(review.getUniversity().getId());
			}
			if(status==false && isActive==false && isApproved == true){
				calculateTotalAverage(review.getUniversity().getId());
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Review> listAllNeedAcceptReview() {
		listAllReview = reviewRepo.findNeedAcceptReview();
		return listAllReview;
	}

	@Override
	public int numberOfReview() {
		return reviewRepo.numberOfReviewNeedAccept();
	}

	@Override
	public boolean checkReviewedUni(int unversityId, int userId) {
		review = reviewRepo.checkReviewed(unversityId, userId);
		if(review == null){
			return false;
		}
		return true;
	}

	@Override
	public List<Review> listReview() {
		listAllReview = reviewRepo.listReivewAccepted();
		return listAllReview;
	}
}
