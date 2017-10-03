package com.unistart.entities;
// Generated Sep 25, 2017 1:40:32 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BlockOfMajor generated by hbm2java
 */
@Entity
@Table(name = "BlockOfMajor", schema = "dbo", catalog = "University")
public class BlockOfMajor implements java.io.Serializable {

	private int id;
	private Block block;
	private Major major;
	private Boolean isActive;

	public BlockOfMajor() {
	}

	public BlockOfMajor(int id) {
		this.id = id;
	}

	public BlockOfMajor(int id, Block block, Major major, Boolean isActive) {
		this.id = id;
		this.block = block;
		this.major = major;
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
	@JoinColumn(name = "BlockId")
	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MajorId")
	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
