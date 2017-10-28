package com.unistart.entities.customentities;

import com.unistart.entities.Location;
import com.unistart.entities.University;

public class LocationUniversity {
	private University university;
	private Location location;
	public LocationUniversity() {
		super();
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
