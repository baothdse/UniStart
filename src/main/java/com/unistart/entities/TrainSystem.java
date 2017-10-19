package com.unistart.entities;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "TrainSystem", schema = "dbo", catalog = "University")
public class TrainSystem {

	private Integer id;
	private String name;
	private boolean isActive;
	private Set<University> university = new HashSet<University>(0);
	
	public TrainSystem() {
		super();
	}

	public TrainSystem(Integer id, String name, boolean isActive, Set<University> university) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.university = university;
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

	@Column(name = "Name", nullable = false, length = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IsActive", nullable = false)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainSystem")
	public Set<University> getUniversity() {
		return university;
	}

	public void setUniversity(Set<University> university) {
		this.university = university;
	}

}
