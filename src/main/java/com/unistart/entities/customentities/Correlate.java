package com.unistart.entities.customentities;

import java.util.Comparator;

public class Correlate implements Comparator<Correlate>{

	private int numberOfSameMajor;
	private int universityId;
	
	public Correlate() {
		super();
	}
	public Correlate(int numberOfSameMajor, int universityId) {
		super();
		this.numberOfSameMajor = numberOfSameMajor;
		this.universityId = universityId;
	}
	public int getNumberOfSameMajor() {
		return numberOfSameMajor;
	}
	public void setNumberOfSameMajor(int numberOfSameMajor) {
		this.numberOfSameMajor = numberOfSameMajor;
	}
	public int getUniversityId() {
		return universityId;
	}
	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}
	@Override
	public int compare(Correlate a, Correlate b) {
		return b.numberOfSameMajor - a.numberOfSameMajor;
	}
}