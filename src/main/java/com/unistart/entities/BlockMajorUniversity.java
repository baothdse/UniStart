package com.unistart.entities;
// Generated Sep 25, 2017 1:40:32 AM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BlockMajorUniversity generated by hbm2java
 */
@Entity
@Table(name = "BlockMajorUniversity", schema = "dbo", catalog = "University")
public class BlockMajorUniversity implements java.io.Serializable {

	private int id;
	private Block block;
	private MajorUniversity majorUniversity;
	private byte[] blockMajorUniversitiesKey;
	private Set<ScoreHistory> scoreHistories = new HashSet<ScoreHistory>(0);
	private Set<ScoreHistory> scoreHistories_1 = new HashSet<ScoreHistory>(0);

	public BlockMajorUniversity() {
	}

	public BlockMajorUniversity(int id, Block block, MajorUniversity majorUniversity) {
		this.id = id;
		this.block = block;
		this.majorUniversity = majorUniversity;
	}

	public BlockMajorUniversity(int id, Block block, MajorUniversity majorUniversity, byte[] blockMajorUniversitiesKey,
			Set<ScoreHistory> scoreHistories, Set<ScoreHistory> scoreHistories_1) {
		this.id = id;
		this.block = block;
		this.majorUniversity = majorUniversity;
		this.blockMajorUniversitiesKey = blockMajorUniversitiesKey;
		this.scoreHistories = scoreHistories;
		this.scoreHistories_1 = scoreHistories_1;
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

	@Column(name = "blockMajorUniversities_KEY")
	public byte[] getBlockMajorUniversitiesKey() {
		return this.blockMajorUniversitiesKey;
	}

	public void setBlockMajorUniversitiesKey(byte[] blockMajorUniversitiesKey) {
		this.blockMajorUniversitiesKey = blockMajorUniversitiesKey;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blockMajorUniversity")
	public Set<ScoreHistory> getScoreHistories() {
		return this.scoreHistories;
	}

	public void setScoreHistories(Set<ScoreHistory> scoreHistories) {
		this.scoreHistories = scoreHistories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blockMajorUniversity")
	public Set<ScoreHistory> getScoreHistories_1() {
		return this.scoreHistories_1;
	}

	public void setScoreHistories_1(Set<ScoreHistory> scoreHistories_1) {
		this.scoreHistories_1 = scoreHistories_1;
	}

}
