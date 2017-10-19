package com.unistart.services.interfaces;

import com.unistart.entities.customentities.MajorPoint;

public interface ReviewMajorUniInterface {
	boolean saveReviewMajorUni(int majorId, int userId,int starTeaching,int starCareer, boolean isRecomment);
	MajorPoint getPointById(int majorUniId);
	public void calculateTotalAverage();
	boolean checkReviewUniMajor(int majorUniId, int userId);
}
