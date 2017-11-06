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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"isActive"})
@Table(name = "Question_Answer", schema = "dbo", catalog = "University")
public class QuestionAnswer implements java.io.Serializable {
	private Integer id;
	private String title;
	private String content;
	private Integer vote;
	private Integer type;
	private Integer count;
	private Users users;
	private Integer parentId;
	private Boolean isActive;
	private Date createdDateTime;
	private Date lastUpdatedTime;
	private boolean isVoteByUser;
	private int totalAnswer;
	
	public QuestionAnswer(Integer id, String title, String content, Integer vote, Integer type, Users users,
			Integer parentId, Boolean isActive, Date createdDateTime, Date lastUpdatedTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.vote = vote;
		this.type = type;
		this.users = users;
		this.parentId = parentId;
		this.isActive = isActive;
		this.createdDateTime = createdDateTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public QuestionAnswer() {
		super();
	}
	public QuestionAnswer(int count) {
		this.count = count;
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
	@Column(name = "Title", nullable = true)
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@Column(name = "ParentId", nullable = false)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId (Integer parentId) {
		this.parentId = parentId;
	}
	@Column(name = "IsActive", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@Column(name = "CreatedDateTime", nullable = false)
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	@Column(name = "LastUpdatedTime")
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	@Transient
	public int getTotalAnswer() {
		return totalAnswer;
	}
	public void setTotalAnswer(int totalAnswer) {
		this.totalAnswer = totalAnswer;
	}
	@Transient
	public boolean isVoteByUser() {
		return isVoteByUser;
	}
	public void setVoteByUser(boolean isVoteByUser) {
		this.isVoteByUser = isVoteByUser;
	}
	

	
}
