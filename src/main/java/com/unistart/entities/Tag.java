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
@Table(name = "Tag", schema = "dbo", catalog = "University")
public class Tag implements java.io.Serializable {
     private Integer id;
     private String tagName;
     private QuestionAnswer questionAnswer;
	public Tag(Integer id, String tagName, QuestionAnswer questionAnswer) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.questionAnswer = questionAnswer;
	}
	public Tag() {
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
	
	@Column(name = "TagName", nullable = false)
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QuestionAnswerId")
	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
     
     
}
