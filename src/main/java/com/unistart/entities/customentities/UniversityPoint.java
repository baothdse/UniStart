package com.unistart.entities.customentities;

import java.io.Serializable;
import java.util.Comparator;

public class UniversityPoint implements Serializable, Comparator<UniversityPoint>{
	private int universityId;
	private double starCare;
	private double starTeaching;
	private double starSocieties;
	private double starFacilities;
	private double starCareer;
	private Double recommentPoint;
	private int totalReview;
	public UniversityPoint(){
		
	}
	public UniversityPoint(double starCare, double starTeaching, double starSocieties, double starFacilities,
			double starCareer, double recommentPoint) {
		super();
		this.starCare = starCare;
		this.starTeaching = starTeaching;
		this.starSocieties = starSocieties;
		this.starFacilities = starFacilities;
		this.starCareer = starCareer;
		this.recommentPoint = recommentPoint;
		
	}
	public UniversityPoint(int universityId, double starCare, double starTeaching, double starSocieties,
			double starFacilities, double starCareer,int totalReview) {
		super();
		this.universityId = universityId;
		this.starCare = starCare;
		this.starTeaching = starTeaching;
		this.starSocieties = starSocieties;
		this.starFacilities = starFacilities;
		this.starCareer = starCareer;
		this.totalReview = totalReview;
	}
	public UniversityPoint(int universityId, double starCare, double starTeaching, double starSocieties,
			double starFacilities, double starCareer,int totalReview, double recommentPoint) {
		super();
		this.universityId = universityId;
		this.starCare = starCare;
		this.starTeaching = starTeaching;
		this.starSocieties = starSocieties;
		this.starFacilities = starFacilities;
		this.starCareer = starCareer;
		this.totalReview = totalReview;
		this.recommentPoint = recommentPoint;
	}
	public int getUniversityId() {
		return universityId;
	}
	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}
	public double getStarCare() {
		return starCare;
	}
	public void setStarCare(double starCare) {
		this.starCare = starCare;
	}
	public double getStarTeaching() {
		return starTeaching;
	}
	public void setStarTeaching(double starTeaching) {
		this.starTeaching = starTeaching;
	}
	public double getStarSocieties() {
		return starSocieties;
	}
	public void setStarSocieties(double starSocieties) {
		this.starSocieties = starSocieties;
	}
	public double getStarFacilities() {
		return starFacilities;
	}
	public void setStarFacilities(double starFacilities) {
		this.starFacilities = starFacilities;
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
	@Override
	public int compare(UniversityPoint a, UniversityPoint b) {
		return b.recommentPoint.compareTo(a.recommentPoint);
	}
}
