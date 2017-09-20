package com.unistart.entities;
// Generated Sep 20, 2017 9:59:41 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReviewLike generated by hbm2java
 */
@Entity
@Table(name = "ReviewLike", schema = "dbo", catalog = "University")
public class ReviewLike implements java.io.Serializable {

	private int id;
	private Candidate candidate;
	private Review review;
	private Boolean isLike;
	private Boolean isActive;

	public ReviewLike() {
	}

	public ReviewLike(int id) {
		this.id = id;
	}

	public ReviewLike(int id, Candidate candidate, Review review, Boolean isLike, Boolean isActive) {
		this.id = id;
		this.candidate = candidate;
		this.review = review;
		this.isLike = isLike;
		this.isActive = isActive;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CandidateId")
	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReviewId")
	public Review getReview() {
		return this.review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Column(name = "IsLike")
	public Boolean getIsLike() {
		return this.isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
