package com.unistart.entities;
// Generated Oct 1, 2017 10:03:30 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name = "Location", schema = "dbo", catalog = "University")
public class Location implements java.io.Serializable {

	private Integer id;
	private String locationName;
	private String locationCode;
	private Boolean isActive;
	private Set<University> universities = new HashSet<University>(0);

	public Location() {
	}

	public Location(Integer id) {
		this.id = id;
	}
	
	public Location(Integer id, String locationName) {
		super();
		this.id = id;
		this.locationName = locationName;
	}

	public Location(Integer id, String locationName, String locationCode, Boolean isActive,
			Set<University> universities) {
		this.id = id;
		this.locationName = locationName;
		this.locationCode = locationCode;
		this.isActive = isActive;
		this.universities = universities;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LocationName")
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Column(name = "LocationCode")
	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	@Column(name = "IsActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "location")
	public Set<University> getUniversities() {
		return this.universities;
	}

	public void setUniversities(Set<University> universities) {
		this.universities = universities;
	}


}
