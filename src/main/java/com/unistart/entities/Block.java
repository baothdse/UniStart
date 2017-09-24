package com.unistart.entities;
// Generated Sep 25, 2017 1:40:32 AM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
	private Set<BlockOfMajor> blockOfMajors = new HashSet<BlockOfMajor>(0);
	private Set<BlockOfMajor> blockOfMajors_1 = new HashSet<BlockOfMajor>(0);
	private Set<BlockMajorUniversity> blockMajorUniversities = new HashSet<BlockMajorUniversity>(0);
	private Set<BlockMajorUniversity> blockMajorUniversities_1 = new HashSet<BlockMajorUniversity>(0);

	public Block() {
	}

	public Block(int id) {
		this.id = id;
	}

	public Block(int id, Serializable blockName, Serializable description, Boolean isActive,
			Set<BlockOfMajor> blockOfMajors, Set<BlockOfMajor> blockOfMajors_1,
			Set<BlockMajorUniversity> blockMajorUniversities, Set<BlockMajorUniversity> blockMajorUniversities_1) {
		this.id = id;
		this.blockName = blockName;
		this.description = description;
		this.isActive = isActive;
		this.blockOfMajors = blockOfMajors;
		this.blockOfMajors_1 = blockOfMajors_1;
		this.blockMajorUniversities = blockMajorUniversities;
		this.blockMajorUniversities_1 = blockMajorUniversities_1;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
	public Set<BlockMajorUniversity> getBlockMajorUniversities() {
		return this.blockMajorUniversities;
	}

	public void setBlockMajorUniversities(Set<BlockMajorUniversity> blockMajorUniversities) {
		this.blockMajorUniversities = blockMajorUniversities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
	public Set<BlockMajorUniversity> getBlockMajorUniversities_1() {
		return this.blockMajorUniversities_1;
	}

	public void setBlockMajorUniversities_1(Set<BlockMajorUniversity> blockMajorUniversities_1) {
		this.blockMajorUniversities_1 = blockMajorUniversities_1;
	}

}
