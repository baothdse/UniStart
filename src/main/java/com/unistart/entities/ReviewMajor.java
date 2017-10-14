package com.unistart.entities;

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

@Entity
@Table(name = "ReviewMajor", schema = "dbo", catalog = "University")
public class ReviewMajor {

	private Integer id;
	private MajorUniversity majorUniversity;
	private Users users;
	private Integer starTeaching;
	private Integer starCareer;
	private Boolean isRecomment;
	private Boolean isActive;
	
	
	public ReviewMajor() {
		super();
	}


	public ReviewMajor(Integer id, MajorUniversity majorUniversity, Users users, Integer starTeaching,
			Integer starCareer, Boolean isRecomment, Boolean isActive) {
		super();
		this.id = id;
		this.majorUniversity = majorUniversity;
		this.users = users;
		this.starTeaching = starTeaching;
		this.starCareer = starCareer;
		this.isRecomment = isRecomment;
		this.isActive = isActive;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MajorUniId", nullable = false)
	public MajorUniversity getMajorUniversity() {
		return majorUniversity;
	}


	public void setMajorUniversity(MajorUniversity majorUniversity) {
		this.majorUniversity = majorUniversity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId", nullable = false)
	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "StarTeaching")
	public Integer getStarTeaching() {
		return starTeaching;
	}


	public void setStarTeaching(Integer starTeaching) {
		this.starTeaching = starTeaching;
	}

	@Column(name = "StarCareer")
	public Integer getStarCareer() {
		return starCareer;
	}


	public void setStarCareer(Integer starCareer) {
		this.starCareer = starCareer;
	}

	@Column(name = "IsRecomment")
	public Boolean getIsRecomment() {
		return isRecomment;
	}


	public void setIsRecomment(Boolean isRecomment) {
		this.isRecomment = isRecomment;
	}

	@Column(name = "IsActive")
	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
