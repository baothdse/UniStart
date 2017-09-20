package com.unistart.entities;
// Generated Sep 17, 2017 1:40:06 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * University generated by hbm2java
 */
@Entity
@Table(name = "University", schema = "dbo", catalog = "University")
public class University implements java.io.Serializable {

	private int id;
	
	private String code;
	private String email;
	private String phone;
	private String logo;
	private String description;
	private String image;
	private Integer priority;
	private Boolean isActive;
	private Set<Location> locations = new HashSet<Location>(0);
	private Set<Users> users = new HashSet<Users>(0);
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<MajorUniversity> majorUniversities = new HashSet<MajorUniversity>(0);

	public University() {
	}

	public University(int id, String code) {
		this.id = id;
		this.code = code;
	}

	public University(int id, String code, String email, String phone, String logo,
			String description, String image, Integer priority, Boolean isActive, Set<Location> locations,
			Set<Users> users, Set<Review> reviews, Set<MajorUniversity> majorUniversities) {
		this.id = id;
		this.code = code;
		this.email = email;
		this.phone = phone;
		this.logo = logo;
		this.description = description;
		this.image = image;
		this.priority = priority;
		this.isActive = isActive;
		this.locations = locations;
		this.users = users;
		this.reviews = reviews;
		this.majorUniversities = majorUniversities;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Code", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Logo")
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "Priority")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
	public Set<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
	public Set<Users> getUsers() {
		return this.users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
	public Set<MajorUniversity> getMajorUniversities() {
		return this.majorUniversities;
	}

	public void setMajorUniversities(Set<MajorUniversity> majorUniversities) {
		this.majorUniversities = majorUniversities;
	}

}
