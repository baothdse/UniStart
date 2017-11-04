package com.unistart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Favorite", schema = "dbo", catalog = "University")
public class Favorite implements java.io.Serializable{
      private Integer id;
      private Users user;
      private MajorUniversity majorUni;
	public Favorite() {
		super();
	}
	public Favorite(Integer id, Users user, MajorUniversity majorUni) {
		super();
		this.id = id;
		this.user = user;
		this.majorUni = majorUni;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId")
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MajorUniversityId")
	public MajorUniversity getMajorUni() {
		return majorUni;
	}
	public void setMajorUni(MajorUniversity majorUni) {
		this.majorUni = majorUni;
	}
      
      
}
