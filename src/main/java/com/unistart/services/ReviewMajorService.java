package com.unistart.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.MajorUniversity;
import com.unistart.entities.ReviewMajor;
import com.unistart.entities.University;
import com.unistart.entities.Users;
import com.unistart.entities.customentities.MajorPoint;
import com.unistart.entities.customentities.UniversityPoint;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.ReviewMajorRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.MajorServiceInterface;
import com.unistart.services.interfaces.ReviewMajorUniInterface;

@Service
@Transactional
public class ReviewMajorService implements ReviewMajorUniInterface{
	
	@Autowired
	private ReviewMajorRepository reviewMajorRepo;
	
	@Autowired
	private MajorUniRepository majorUniRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MajorServiceInterface majorUniService;
	
	private ReviewMajor review;
	private List<MajorPoint> listMajorPoint;
	private Double starTeaching;
	private Double starCareer;
	private Double recommentPoint;
	private int totalReview;
	
	
	public List<MajorPoint> getListMajorPoint() {
		return listMajorPoint;
	}
	public void setListMajorPoint(List<MajorPoint> listMajorPoint) {
		this.listMajorPoint = listMajorPoint;
	}

	public Double getRecommentPoint() {
		return recommentPoint;
	}
	public void setRecommentPoint(Double recommentPoint) {
		this.recommentPoint = recommentPoint;
	}
	
	public void calculateRecomment(int majorUniId) {
		int totalRecomment = reviewMajorRepo.countReview(majorUniId);
		int trueComment = reviewMajorRepo.countRecommended(majorUniId);
		if (totalRecomment != 0) {
			double averagePercent =  ((double)(trueComment)/totalRecomment) * 100;
			System.out.println(averagePercent); 
			setRecommentPoint((double)Math.round(averagePercent)); 
		}else {
			setRecommentPoint(null);
		}
	}
	public double calculateStarTeaching(int majorUniId) {
		int totalTeachingReview = reviewMajorRepo.countReview(majorUniId);
		if(reviewMajorRepo.sumStarTeaching(majorUniId) != null && totalTeachingReview != 0){
			int totalPoint = reviewMajorRepo.sumStarTeaching(majorUniId);
			double average =  (double) totalPoint / totalTeachingReview;
			return average;
		}
		return -1;
	}
	
	public double calculateStarCareer(int majorUniId) {
		int totalCareerReview = reviewMajorRepo.countReview(majorUniId);
		System.out.println(totalCareerReview);
		if(reviewMajorRepo.sumStarCareer(majorUniId) != null && totalCareerReview != 0){
			int totalPoint = reviewMajorRepo.sumStarCareer(majorUniId);
			System.out.println("total Carerew " + totalPoint);
			double average =  (double) totalPoint / totalCareerReview;
			return average;
		}
		return -1;
	}
	
	@Override
	@PostConstruct
	@Scheduled(cron = "0 0 2 * * *")
	public void calculateTotalAverage() {
		List<MajorUniversity> listId = majorUniService.getListId();
		List<MajorPoint> listPoint = new ArrayList<MajorPoint>();
		MajorPoint point = null;
		for (int index = 0; index < listId.size(); index++) {	
			System.out.println("ID major" + listId.get(index).getId());
			starTeaching = calculateStarTeaching(listId.get(index).getId());
			starCareer = calculateStarCareer(listId.get(index).getId());
			calculateRecomment(listId.get(index).getId());
			totalReview = reviewMajorRepo.countReview(listId.get(index).getId());
			if(starTeaching != -1 && starCareer != -1){
				if(getRecommentPoint() == null) {
					point = new MajorPoint(listId.get(index).getId(), starTeaching,starCareer,totalReview);
				} else {
					point = new MajorPoint(listId.get(index).getId(), starTeaching, starCareer, totalReview, getRecommentPoint());
				}
				listPoint.add(point);
			}
		}
		setListMajorPoint(listPoint);
	}
	
	@Override
	public boolean saveReviewMajorUni(int majorId, int userId, int starTeaching, int starCareer, boolean isRecomment) {
        MajorUniversity majorUni = majorUniRepo.findById(majorId);
        Users user = userRepo.findById(userId);
        if(majorUni != null && user != null){
        	review = new ReviewMajor();
        	review.setMajorUniversity(majorUni);
        	review.setUsers(user);
        	review.setStarTeaching(starTeaching);
        	review.setStarCareer(starCareer);
        	review.setIsRecomment(isRecomment);
        	review.setIsActive(true);
        	reviewMajorRepo.save(review);
        	return true;
        }
		return false;
	}
	@Override
	public MajorPoint getPointById(int majorUniId) {
		MajorPoint majorPoint = null;
		for (int index = 0; index < listMajorPoint.size(); index++) {
			if(majorUniId == listMajorPoint.get(index).getMajorUniId()) {
				majorPoint = listMajorPoint.get(index);
			}
		}
		return majorPoint;
	}
	@Override
	public boolean checkReviewUniMajor(int majorUniId, int userId) {
		review = reviewMajorRepo.checkReviewed(majorUniId, userId);
		if(review == null){
			return false;
		}
		return true;
	}

}
