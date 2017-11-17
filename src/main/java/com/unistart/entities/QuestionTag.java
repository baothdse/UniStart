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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "QuestionTag", schema = "dbo", catalog = "University")
public class QuestionTag {
	private Integer id;
	@JsonBackReference
	private QuestionAnswer qa;
	private Tag tag;
	//private Tag otherTag;
	
	
	public QuestionTag() {
		super();
	}
	public QuestionTag(Integer id, QuestionAnswer qa, Tag tag) {
		super();
		this.id = id;
		this.qa = qa;
		this.tag = tag;
	}
	
	public QuestionTag(Integer id, Tag tag) {
		super();
		this.id = id;
		this.tag = tag;
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
	@JoinColumn(name = "QuestionId")
	public QuestionAnswer getQa() {
		return qa;
	}
	public void setQa(QuestionAnswer qa) {
		this.qa = qa;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TagId")
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "UniversityId")
//	public University getUniversity() {
//		return university;
//	}
//	public void setUniversity(University university) {
//		this.university = university;
//	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "TagId")
//	public Tag getOtherTag() {
//		return otherTag;
//	}
//	public void setOtherTag(Tag otherTag) {
//		this.otherTag = otherTag;
//	}
	
	
}
