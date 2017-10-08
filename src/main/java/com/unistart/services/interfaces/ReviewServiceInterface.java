package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Review;
import com.unistart.entities.customentities.UniversityPoint;

public interface ReviewServiceInterface {
	int countStarTeaching (int universityId);
	int countStarFacilities (int universityId);
	int countStarSocieties (int universityId);
	int countStarCare(int universityId);
	int countStarCareer (int universityId);
	void countReviewOfUniversity (int universityId);
	
	void calculateStarTeaching (int universityId);
	void calculateStarFacilities (int universityId);
	void calculateStarSocieties (int universityId);
	void calculateStarCare(int universityId);
	void calculateStarCareer (int universityId);
	
	int countRecomment(int universityId);
	void calculateRecomment(int universityId);
	
	List<UniversityPoint> getListUniversityPoint();
	void calculateTotalAverage();
	
	UniversityPoint getPointById(int universityId);
  
	boolean saveReview(int universityId, int userId,String title, String description, int starTeaching,
			int starFacilities, int starCare, int starSocieties, int starCareer, boolean isRecomment, boolean status);
	List<Review> listReviewOfUniversity(int universityId);
	boolean changeReviewStatus(int id, boolean status, boolean isActive);

	List<Review> listAllNeedAcceptReview();
}
