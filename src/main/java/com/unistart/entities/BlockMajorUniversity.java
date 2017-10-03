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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * BlockMajorUniversity generated by hbm2java
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "BlockMajorUniversity", schema = "dbo", catalog = "University")
public class BlockMajorUniversity implements java.io.Serializable {

	private Integer id;
	private Block block;
	@JsonBackReference
	private MajorUniversity majorUniversity;
	
	@JsonManagedReference
	private Set<ScoreHistory> scoreHistories = new HashSet<ScoreHistory>(0);
	
	public BlockMajorUniversity() {
	}

	public BlockMajorUniversity(Integer id, Block block, MajorUniversity majorUniversity) {
		this.id = id;
		this.block = block;
		this.majorUniversity = majorUniversity;
	}

	public BlockMajorUniversity(Integer id, Block block, MajorUniversity majorUniversity, byte[] blockMajorUniversitiesKey,
			Set<ScoreHistory> scoreHistories, Set<ScoreHistory> scoreHistories_1) {
		this.id = id;
		this.block = block;
		this.majorUniversity = majorUniversity;
		this.scoreHistories = scoreHistories;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLockId", nullable = false)
	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MajorUniversityID", nullable = false)
	public MajorUniversity getMajorUniversity() {
		return this.majorUniversity;
	}

	public void setMajorUniversity(MajorUniversity majorUniversity) {
		this.majorUniversity = majorUniversity;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blockMajorUniversity")
	public Set<ScoreHistory> getScoreHistories() {
		return this.scoreHistories;
	}

	public void setScoreHistories(Set<ScoreHistory> scoreHistories) {
		this.scoreHistories = scoreHistories;
	}

}
