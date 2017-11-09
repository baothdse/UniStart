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
      private Users users;
      private MajorUniversity majorUniversity;
      private String majorName;
      private String univeristyName;
      private Integer majorUniId;
	public Favorite() {
		super();
	}
	public Favorite(Integer id, Users user, MajorUniversity majorUni) {
		super();
		this.id = id;
		this.users = user;
		this.majorUniversity = majorUni;
	}
	
	public Favorite(MajorUniversity majorUni) {
		super();
		this.majorUniversity = majorUni;
	}
	
	public Favorite(Integer id, MajorUniversity majorUni){
		this.id = id;
		this.majorUniId = majorUni.getId();
		this.majorName = majorUni.getMajor().getMajorName();
		this.univeristyName = majorUni.getUniversity().getName();
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
		return users;
	}
	public void setUser(Users user) {
		this.users = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MajorUniversityId")
	public MajorUniversity getMajorUni() {
		return majorUniversity;
	}
	public void setMajorUni(MajorUniversity majorUni) {
		this.majorUniversity = majorUni;
	}
      
      
}
