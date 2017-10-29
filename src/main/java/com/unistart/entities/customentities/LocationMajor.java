package com.unistart.entities.customentities;

import java.util.List;

import com.unistart.entities.Location;
import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;

public class LocationMajor {
	private int[] majorId;
	private int majorID;
	private Location location;
	private University university;
	private List<MajorUniversity> majorUnis;
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public LocationMajor() {
		super();
	}
	

	public LocationMajor(int[] majorId, Location location, University university) {
		super();
		this.majorId = majorId;
		this.location = location;
		this.university = university;
	}

	public int[] getMajorId() {
		return majorId;
	}

	public void setMajorId(int[] majorId) {
		this.majorId = majorId;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	public int getMajorID() {
		return majorID;
	}

	public void setMajorID(int majorID) {
		this.majorID = majorID;
	}

	public List<MajorUniversity> getMajorUnis() {
		return majorUnis;
	}

	public void setMajorUnis(List<MajorUniversity> majorUnis) {
		this.majorUnis = majorUnis;
	}
	
}
