package com.unistart.entities.customentities;

import java.util.List;

import com.unistart.entities.Location;
import com.unistart.entities.Major;

public class LocationMajor {
	private List<Major> majors;
	private Location location;
	public LocationMajor() {
		super();
	}
	
	public LocationMajor(List<Major> majors, Location location) {
		super();
		this.majors = majors;
		this.location = location;
	}
	public List<Major> getMajors() {
		return majors;
	}
	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
