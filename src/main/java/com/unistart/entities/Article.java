package com.unistart.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Article", schema = "dbo", catalog = "University")
public class Article implements Serializable{
	private Integer id;
	private String title;
	private String description;
	private String contents;
	private String image;
	private Status status;
	private Users users;
	private Boolean isActive;
	
	public Article() {
		
	}
	
	public Article(Integer id, String title, String description, String contents, String image, Status status,
			Users user, Boolean isActive) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.contents = contents;
		this.image = image;
		this.status = status;
		this.users = user;
		this.isActive = isActive;
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
	
	@Column(name = "Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "Contents", nullable = false)
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Column(name = "Image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "StatusId")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "IsActive")
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
