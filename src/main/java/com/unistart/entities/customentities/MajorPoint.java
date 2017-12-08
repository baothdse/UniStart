package com.unistart.entities.customentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unistart.entities.MajorUniversity;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ReviewMajorAverage", schema = "dbo", catalog = "University")
public class MajorPoint {
	private MajorUniversity majorUniversity;
	private double starTeaching;
	private double starCareer;
	private double recommentPoint;
	private int id;
	private int majorId;
	private int totalReview;
	
	
	public MajorPoint() {
		super();
	}
	public MajorPoint(MajorUniversity majorUniversity, double starTeaching, double starCareer, int totalReview,double recommentPoint) {
		super();
		this.majorUniversity = majorUniversity;
		this.starTeaching = starTeaching;
		this.starCareer = starCareer;
		this.recommentPoint = recommentPoint;
		this.totalReview = totalReview;
	}
	public MajorPoint(int id, int majorId, double starTeaching, double starCareer, int totalReview,double recommentPoint) {
		super();
		this.id = id;
		this.majorId = majorId;
		this.starTeaching = starTeaching;
		this.starCareer = starCareer;
		this.totalReview = totalReview;
		this.recommentPoint = recommentPoint;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MajorUniversityId")
	public MajorUniversity getMajorUniversity() {
		return majorUniversity;
	}
	public void setMajorUniversity(MajorUniversity majorUniversity) {
		this.majorUniversity = majorUniversity;
	}
	@Column(name = "AveStarTeaching", nullable = false)
	public double getStarTeaching() {
		return starTeaching;
	}
	public void setStarTeaching(double starTeaching) {
		this.starTeaching = starTeaching;
	}
	@Column(name = "AveStarCareer", nullable = false)
	public double getStarCareer() {
		return starCareer;
	}
	public void setStarCareer(double starCareer) {
		this.starCareer = starCareer;
	}
	@Column(name = "AveRecomment", nullable = false)
	public double getRecommentPoint() {
		return recommentPoint;
	}
	public void setRecommentPoint(double recommentPoint) {
		this.recommentPoint = recommentPoint;
	}
	@Column(name = "TotalReview", nullable = false)
	public int getTotalReview() {
		return totalReview;
	}
	public void setTotalReview(int totalReview) {
		this.totalReview = totalReview;
	}
	
	
}
