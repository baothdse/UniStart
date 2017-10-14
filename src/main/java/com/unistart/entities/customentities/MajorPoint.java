package com.unistart.entities.customentities;

public class MajorPoint {
	private int majorUniId;
	private double starTeaching;
	private double starCareer;
	private double recommentPoint;
	private int totalReview;
	
	
	public MajorPoint() {
		super();
	}
	public MajorPoint(int majorId, double starTeaching, double starCareer, int totalReview,double recommentPoint) {
		super();
		this.majorUniId = majorId;
		this.starTeaching = starTeaching;
		this.starCareer = starCareer;
		this.recommentPoint = recommentPoint;
		this.totalReview = totalReview;
	}
	public MajorPoint(int majorId, double starTeaching, double starCareer, int totalReview) {
		super();
		this.majorUniId = majorId;
		this.starTeaching = starTeaching;
		this.starCareer = starCareer;
		this.totalReview = totalReview;
	}
	public int getMajorUniId() {
		return majorUniId;
	}
	public void setMajorUniId(int majorId) {
		this.majorUniId = majorId;
	}
	public double getStarTeaching() {
		return starTeaching;
	}
	public void setStarTeaching(double starTeaching) {
		this.starTeaching = starTeaching;
	}
	public double getStarCareer() {
		return starCareer;
	}
	public void setStarCareer(double starCareer) {
		this.starCareer = starCareer;
	}
	public double getRecommentPoint() {
		return recommentPoint;
	}
	public void setRecommentPoint(double recommentPoint) {
		this.recommentPoint = recommentPoint;
	}
	public int getTotalReview() {
		return totalReview;
	}
	public void setTotalReview(int totalReview) {
		this.totalReview = totalReview;
	}
	
	
}
