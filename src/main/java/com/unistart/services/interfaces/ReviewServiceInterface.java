package com.unistart.services.interfaces;

public interface ReviewServiceInterface {

	boolean saveReview(int universityId, int userId, String description, int starTeaching,
			int starFacilities, int starCare, int starSocieties, int starCareer, boolean isRecomment, boolean status);

	boolean changeReviewStatus(int id, boolean status, boolean isActive);
}
