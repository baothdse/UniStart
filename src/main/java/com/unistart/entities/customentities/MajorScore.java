package com.unistart.entities.customentities;

import java.util.List;

import com.unistart.entities.Block;
import com.unistart.entities.BlockMajorUniversity;
import com.unistart.entities.Major;
import com.unistart.entities.ScoreHistory;

public class MajorScore {
	private int blockId;
	private String blockName;
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	private Double score;
	private int year;
	private String description;
	private int barem;
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBarem() {
		return barem;
	}
	public void setBarem(int barem) {
		this.barem = barem;
	}
	
}
