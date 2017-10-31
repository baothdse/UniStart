package com.unistart.entities.customentities;

import java.util.List;

import com.unistart.entities.Review;
import com.unistart.entities.University;

public class ReviewUniversity {
	private List<Review> review;
	private Integer universityId;
	
	
	public ReviewUniversity(List<Review> review, Integer universityId) {
		super();
		this.review = review;
		this.universityId = universityId;
	}
	public List<Review> getReview() {
		return review;
	}
	public void setReview(List<Review> review) {
		this.review = review;
	}
	public Integer getUniversityId() {
		return universityId;
	}
	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}

	
}
