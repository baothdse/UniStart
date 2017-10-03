package com.unistart.entities.customentities;

import java.util.List;

public class MajorBlockScore {
	private int majorUniId;
	private String blockName;
  public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

public int getMajorUniId() {
		return majorUniId;
	}

	public void setMajorUniId(int majorUniId) {
		this.majorUniId = majorUniId;
	}

private List<MajorScore> majorScore;

public List<MajorScore> getMajorScore() {
	return majorScore;
}

public void setMajorScore(List<MajorScore> majorScore) {
	this.majorScore = majorScore;
}
  
}
