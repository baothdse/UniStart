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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Article", schema = "dbo", catalog = "University")
public class Article {
	private Integer id;
	private String code;
	private String title;
	private String description;
	private Date createDate;
	private String contents;
	private String image;
	private University university;
	private Boolean isActive;
	
	
	public Article() {
		super();
	}


	public Article(Integer id, String code, String title) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
	}


	public Article(Integer id, String code, String title, String description, String contents, String image, Date createDate,
			University university, Boolean isActive) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.description = description;
		this.contents = contents;
		this.image = image;
		this.createDate = createDate;
		this.university = university;
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

	@Column(name = "Code", nullable = false)
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "createDate", length = 100)
	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UniversityId")
	public University getUniversity() {
		return university;
	}


	public void setUniversity(University university) {
		this.university = university;
	}

	@Column(name = "IsActive")
	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
