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
 * Users generated by hbm2java
 */
@Entity
@Table(name = "Users", schema = "dbo", catalog = "University")
public class Users implements java.io.Serializable {

	private int id;
	@JsonIgnore
	private Role role;
	private University university;
	private String username;
	private String password;
	private String name;
	private String image;
	private String email;
	private boolean isActive;
	private Set<ReviewLike> reviewLikes = new HashSet<ReviewLike>(0);
	private Set<Provider> providers = new HashSet<Provider>(0);
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<Mbtiresult> mbtiresults = new HashSet<Mbtiresult>(0);

	public Users() {
	}

	public Users(int id, Role role, String username, String password, boolean isActive) {
		this.id = id;
		this.role = role;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	public Users(Role role, String username,String name, String image, String email,  String password) {
		this.role = role;
		this.username = username;
		this.name = name;
		this.image = image;
		this.email = email;
		this.password = password;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoleID", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UniversityId")
	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Column(name = "Username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IsActive", nullable = false)
	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<ReviewLike> getReviewLikes() {
		return this.reviewLikes;
	}

	public void setReviewLikes(Set<ReviewLike> reviewLikes) {
		this.reviewLikes = reviewLikes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Provider> getProviders() {
		return this.providers;
	}

	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Mbtiresult> getMbtiresults() {
		return this.mbtiresults;
	}

	public void setMbtiresults(Set<Mbtiresult> mbtiresults) {
		this.mbtiresults = mbtiresults;
	}

}
