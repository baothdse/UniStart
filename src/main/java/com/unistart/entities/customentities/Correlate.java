package com.unistart.entities.customentities;

import java.util.Comparator;
import java.util.List;

import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;

public class Correlate implements Comparator<Correlate>{

	private int numberOfSameMajor;
	private List<MajorUniversity> listMajorX;
	private List<MajorUniversity> listMajorY;
	private int universityId;
	public Correlate() {
		super();
	}
	public Correlate(int numberOfSameMajor, int universityId) {
		super();
		this.numberOfSameMajor = numberOfSameMajor;
		this.universityId = universityId;
	}
	
	public List<MajorUniversity> getListMajorX() {
		return listMajorX;
	}
	public void setListMajorX(List<MajorUniversity> listMajorX) {
		this.listMajorX = listMajorX;
	}
	public List<MajorUniversity> getListMajorY() {
		return listMajorY;
	}
	public void setListMajorY(List<MajorUniversity> listMajorY) {
		this.listMajorY = listMajorY;
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