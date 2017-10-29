package com.unistart.entities.customentities;


import java.util.Comparator;

import com.unistart.entities.University;

public class Pearson implements Comparator<Pearson>{
     private University university;
     private Double r;
     
	public Pearson() {
		super();
	}
	public Pearson(University university, Double r) {
		super();
		this.university = university;
		this.r = r;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	public Double getR() {
		return r;
	}
	public void setR(Double r) {
		this.r = r;
	}
	
	@Override
	public int compare(Pearson a, Pearson b) {
		return b.r.compareTo(a.r);
	}

}
