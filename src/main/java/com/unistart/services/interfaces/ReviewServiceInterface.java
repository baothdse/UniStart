package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Review;
import com.unistart.entities.customentities.UniversityPoint;

public interface ReviewServiceInterface {
	List<UniversityPoint> getListUniversityPoint();
	void calculateTotalAverage();
	
	UniversityPoint getPointById(int universityId);
  
	boolean saveReview(int universityId, int userId,String title, String description, int starTeaching,
			int starFacilities, int starCare, int starSocieties, int starCareer, boolean isRecomment, boolean status);

	List<Review> listReviewOfUniversity(int universityId);
	boolean changeReviewStatus(int id, boolean status, boolean isActive);

	List<Review> listAllNeedAcceptReview();
	
	int numberOfReview();
	
	boolean checkReviewedUni(int unversityId, int userId);
}
