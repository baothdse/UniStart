package com.unistart.entities;
// Generated Sep 25, 2017 1:40:32 AM by Hibernate Tools 4.3.1.Final

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * University generated by hbm2java
 */
@Entity
@Table(name = "University", schema = "dbo", catalog = "University")
public class University implements java.io.Serializable {

	private int id;
	private Location location;
	private String code;
	private String name;
	private String email;
	private String phone;
	private String logo;
	private String description;
	private String image;
	private int priority;
	private Boolean isActive;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<Review> reviews = new HashSet<Review>(0);
	
	@JsonManagedReference
	private Set<MajorUniversity> majorUniversities = new HashSet<MajorUniversity>(0);

	public University() {
		
	}

	public University(int id, String image, String name, int priority) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.priority = priority;
	}

	public University(String code, String name, String email,
			String phone, String logo, String image,int priority, String description, Boolean isActive) {
		this.code = code;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.logo = logo;
		this.image = image;
		this.priority = priority;
		this.description = description;
		this.isActive = isActive;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LocationId")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "Code", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Column(name = "IsActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "university")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "university")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "university")
	public Set<MajorUniversity> getMajorUniversities() {
		return this.majorUniversities;
	}

	public void setMajorUniversities(Set<MajorUniversity> majorUniversities) {
		this.majorUniversities = majorUniversities;
	}

}
