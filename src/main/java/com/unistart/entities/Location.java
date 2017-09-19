package com.unistart.entities;
// Generated Sep 17, 2017 1:40:06 PM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name = "Location", schema = "dbo", catalog = "University")
public class Location implements java.io.Serializable {

	private int id;
	private University university;
	private Serializable locationName;
	private Serializable locationCode;

	public Location() {
	}

	public Location(int id) {
		this.id = id;
	}

	public Location(int id, University university, Serializable locationName, Serializable locationCode) {
		this.id = id;
		this.university = university;
		this.locationName = locationName;
		this.locationCode = locationCode;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UniversityId")
	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Column(name = "LocationName")
	public Serializable getLocationName() {
		return this.locationName;
	}

	public void setLocationName(Serializable locationName) {
		this.locationName = locationName;
	}

	@Column(name = "LocationCode")
	public Serializable getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(Serializable locationCode) {
		this.locationCode = locationCode;
	}

}
