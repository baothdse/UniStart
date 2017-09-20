package com.unistart.entities;
// Generated Sep 20, 2017 9:59:41 AM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Block generated by hbm2java
 */
@Entity
@Table(name = "Block", schema = "dbo", catalog = "University")
public class Block implements java.io.Serializable {

	private int id;
	private Serializable blockName;
	private Serializable description;
	private Boolean isActive;
	private Set<MajorUniversity> majorUniversities = new HashSet<MajorUniversity>(0);
	private Set<BlockOfMajor> blockOfMajors = new HashSet<BlockOfMajor>(0);
	private Set<BlockOfMajor> blockOfMajors_1 = new HashSet<BlockOfMajor>(0);
	private Set<MajorUniversity> majorUniversities_1 = new HashSet<MajorUniversity>(0);
	private Set<MajorUniversity> majorUniversities_2 = new HashSet<MajorUniversity>(0);

	public Block() {
	}

	public Block(int id) {
		this.id = id;
	}

	public Block(int id, Serializable blockName, Serializable description, Boolean isActive,
			Set<MajorUniversity> majorUniversities, Set<BlockOfMajor> blockOfMajors, Set<BlockOfMajor> blockOfMajors_1,
			Set<MajorUniversity> majorUniversities_1, Set<MajorUniversity> majorUniversities_2) {
		this.id = id;
		this.blockName = blockName;
		this.description = description;
		this.isActive = isActive;
		this.majorUniversities = majorUniversities;
		this.blockOfMajors = blockOfMajors;
		this.blockOfMajors_1 = blockOfMajors_1;
		this.majorUniversities_1 = majorUniversities_1;
		this.majorUniversities_2 = majorUniversities_2;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "BlockName")
	public Serializable getBlockName() {
		return this.blockName;
	}

	public void setBlockName(Serializable blockName) {
		this.blockName = blockName;
	}

	@Column(name = "Description")
	public Serializable getDescription() {
		return this.description;
	}

	public void setDescription(Serializable description) {
		this.description = description;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
	public Set<MajorUniversity> getMajorUniversities() {
		return this.majorUniversities;
	}

	public void setMajorUniversities(Set<MajorUniversity> majorUniversities) {
		this.majorUniversities = majorUniversities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
	public Set<BlockOfMajor> getBlockOfMajors() {
		return this.blockOfMajors;
	}

	public void setBlockOfMajors(Set<BlockOfMajor> blockOfMajors) {
		this.blockOfMajors = blockOfMajors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
	public Set<BlockOfMajor> getBlockOfMajors_1() {
		return this.blockOfMajors_1;
	}

	public void setBlockOfMajors_1(Set<BlockOfMajor> blockOfMajors_1) {
		this.blockOfMajors_1 = blockOfMajors_1;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "BlockMajorUniversity", schema = "dbo", catalog = "University", joinColumns = {
			@JoinColumn(name = "BLockId", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "MajorUniversityID", nullable = false, updatable = false) })
	public Set<MajorUniversity> getMajorUniversities_1() {
		return this.majorUniversities_1;
	}

	public void setMajorUniversities_1(Set<MajorUniversity> majorUniversities_1) {
		this.majorUniversities_1 = majorUniversities_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
	public Set<MajorUniversity> getMajorUniversities_2() {
		return this.majorUniversities_2;
	}

	public void setMajorUniversities_2(Set<MajorUniversity> majorUniversities_2) {
		this.majorUniversities_2 = majorUniversities_2;
	}

}
