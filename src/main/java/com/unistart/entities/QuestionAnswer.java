package com.unistart.entities;

import java.util.Date;

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
@Table(name = "Question_Answer", schema = "dbo", catalog = "University")
public class QuestionAnswer implements java.io.Serializable {
	private Integer id;
	private String title;
	private String content;
	private Integer vote;
	private Integer type;
	private Integer count;
	private Users users;
	private University university;
	private Boolean isActive;
	public QuestionAnswer(Integer id, String title, String content, Integer vote, Integer type, Users users,
			University university, Boolean isActive) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.vote = vote;
		this.type = type;
		this.users = users;
		this.university = university;
		this.isActive = isActive;
	}
	public QuestionAnswer() {
		super();
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
	@Column(name = "Title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "Contents", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "Vote")
	public Integer getVote() {
		if(vote == null){
			setVote(0);
		}
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
	@Column(name = "Count", nullable = false)
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Column(name = "Type", nullable = false)
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UniversityId")
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
	@Column(name = "IsActive", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
