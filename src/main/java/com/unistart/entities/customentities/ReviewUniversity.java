package com.unistart.entities.customentities;

import com.unistart.entities.Review;
import com.unistart.entities.University;

public class ReviewUniversity {
	private Review review;
	private University university;
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
}
