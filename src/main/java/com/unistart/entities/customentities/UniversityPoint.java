package com.unistart.entities.customentities;

import java.io.Serializable;
import java.util.Comparator;

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
import com.unistart.entities.University;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ReviewAverage", schema = "dbo", catalog = "University")
public class UniversityPoint implements Serializable, Comparator<UniversityPoint>{
	private int id;
	private University university;
	private double starCare;
	private double starTeaching;
	private double starSocieties;
	private double starFacilities;
	private double starCareer;
	private Double recommentPoint;
	private int totalReview;
	private int universityId;
	public UniversityPoint(){
		
	}
	public UniversityPoint(int universityId, double starCare, double starTeaching, double starSocieties, double starFacilities,
			double starCareer, double recommentPoint,int totalReview) {
		super();
		this.universityId = universityId;
		this.starCare = starCare;
		this.starTeaching = starTeaching;
		this.starSocieties = starSocieties;
		this.starFacilities = starFacilities;
		this.starCareer = starCareer;
		this.recommentPoint = recommentPoint;
		this.totalReview = totalReview;
		
	}
	public UniversityPoint(University university, double starCare, double starTeaching, double starSocieties,
			double starFacilities, double starCareer,int totalReview) {
		super();
		this.university = university;
		this.starCare = starCare;
		this.starTeaching = starTeaching;
		this.starSocieties = starSocieties;
		this.starFacilities = starFacilities;
		this.starCareer = starCareer;
		this.totalReview = totalReview;
	}
	public UniversityPoint(University university, double starCare, double starTeaching, double starSocieties,
			double starFacilities, double starCareer,int totalReview, double recommentPoint) {
		super();
		this.university = university;
		this.starCare = starCare;
		this.starTeaching = starTeaching;
		this.starSocieties = starSocieties;
		this.starFacilities = starFacilities;
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
	@JoinColumn(name = "UniversityId")
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	@Column(name = "AveStarCare", nullable = false)
	public double getStarCare() {
		return starCare;
	}
	public void setStarCare(double starCare) {
		this.starCare = starCare;
	}
	@Column(name = "AveStarTeaching", nullable = false)
	public double getStarTeaching() {
		return starTeaching;
	}
	public void setStarTeaching(double starTeaching) {
		this.starTeaching = starTeaching;
	}
	@Column(name = "AveStarSocieties", nullable = false)
	public double getStarSocieties() {
		return starSocieties;
	}
	public void setStarSocieties(double starSocieties) {
		this.starSocieties = starSocieties;
	}
	@Column(name = "AveStarFacilities", nullable = false)
	public double getStarFacilities() {
		return starFacilities;
	}
	public void setStarFacilities(double starFacilities) {
		this.starFacilities = starFacilities;
	}
	@Column(name = "AveStarCareer", nullable = false)
	public double getStarCareer() {
		return starCareer;
	}
	public void setStarCareer(double starCareer) {
		this.starCareer = starCareer;
	}
	@Column(name = "AveRecomment", nullable = false)
	public Double getRecommentPoint() {
		return recommentPoint;
	}
	public void setRecommentPoint(Double recommentPoint) {
		this.recommentPoint = recommentPoint;
	}
	@Column(name = "TotalReview", nullable = false)
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
