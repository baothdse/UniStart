package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Review;

public interface ReviewServiceInterface {

	boolean saveReview(int universityId, int userId, String description, int starTeaching,
			int starFacilities, int starCare, int starSocieties, int starCareer, boolean isRecomment, boolean status);
	List<Review> listReviewOfUniversity(int universityId);
	boolean changeReviewStatus(int id, boolean status, boolean isActive);
}
