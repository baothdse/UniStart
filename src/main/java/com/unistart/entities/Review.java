package com.unistart.entities;
// Generated Sep 25, 2017 1:40:32 AM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name = "Review", schema = "dbo", catalog = "University")
public class Review implements java.io.Serializable {

	private int id;
	private University university;
	private Users users;
	private String description;
	private int starTeaching;
	private int starFacilities;
	private int starCare;
	private int starSocieties;
	private int starCareer;
	private boolean isRecomment;
	private boolean status;
	private boolean isActive;
	private Set<ReviewLike> reviewLikes = new HashSet<ReviewLike>(0);

	public Review() {
	}

	public Review(int id, University university, Users users) {
		this.id = id;
		this.university = university;
		this.users = users;
	}

	public Review(int id, University university, Users users, String description, Integer starTeaching,
			Integer starFacilities, Integer starCare, Integer starSocieties, Integer starCareer, Boolean isRecomment,
			Boolean status, Boolean isActive, Set<ReviewLike> reviewLikes) {
		this.id = id;
		this.university = university;
		this.users = users;
		this.description = description;
		this.starTeaching = starTeaching;
		this.starFacilities = starFacilities;
		this.starCare = starCare;
		this.starSocieties = starSocieties;
		this.starCareer = starCareer;
		this.isRecomment = isRecomment;
		this.status = status;
		this.isActive = isActive;
		this.reviewLikes = reviewLikes;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UniversityId", nullable = false)
	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "StarTeaching")
	public Integer getStarTeaching() {
		return this.starTeaching;
	}

	public void setStarTeaching(Integer starTeaching) {
		this.starTeaching = starTeaching;
	}

	@Column(name = "StarFacilities")
	public Integer getStarFacilities() {
		return this.starFacilities;
	}

	public void setStarFacilities(Integer starFacilities) {
		this.starFacilities = starFacilities;
	}

	@Column(name = "StarCare")
	public Integer getStarCare() {
		return this.starCare;
	}

	public void setStarCare(Integer starCare) {
		this.starCare = starCare;
	}

	@Column(name = "StarSocieties")
	public Integer getStarSocieties() {
		return this.starSocieties;
	}

	public void setStarSocieties(Integer starSocieties) {
		this.starSocieties = starSocieties;
	}

	@Column(name = "StarCareer")
	public Integer getStarCareer() {
		return this.starCareer;
	}

	public void setStarCareer(Integer starCareer) {
		this.starCareer = starCareer;
	}

	@Column(name = "IsRecomment")
	public Boolean getIsRecomment() {
		return this.isRecomment;
	}

	public void setIsRecomment(Boolean isRecomment) {
		this.isRecomment = isRecomment;
	}

	@Column(name = "Status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "IsActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
	public Set<ReviewLike> getReviewLikes() {
		return this.reviewLikes;
	}

	public void setReviewLikes(Set<ReviewLike> reviewLikes) {
		this.reviewLikes = reviewLikes;
	}

}
