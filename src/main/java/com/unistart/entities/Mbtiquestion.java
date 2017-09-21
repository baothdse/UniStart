package com.unistart.entities;
// Generated Sep 21, 2017 4:14:36 PM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mbtiquestion generated by hbm2java
 */
@Entity
@Table(name = "MBTIQuestion", schema = "dbo", catalog = "University")
public class Mbtiquestion implements java.io.Serializable {

	private int id;
	private Mbtigroup mbtigroup;
	private Serializable code;
	private Serializable questionContent;
	private Serializable option1name;
	private Serializable option2name;
	private Boolean isActive;

	public Mbtiquestion() {
	}

	public Mbtiquestion(int id) {
		this.id = id;
	}

	public Mbtiquestion(int id, Mbtigroup mbtigroup, Serializable code, Serializable questionContent,
			Serializable option1name, Serializable option2name, Boolean isActive) {
		this.id = id;
		this.mbtigroup = mbtigroup;
		this.code = code;
		this.questionContent = questionContent;
		this.option1name = option1name;
		this.option2name = option2name;
		this.isActive = isActive;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MBTIGroupId")
	public Mbtigroup getMbtigroup() {
		return this.mbtigroup;
	}

	public void setMbtigroup(Mbtigroup mbtigroup) {
		this.mbtigroup = mbtigroup;
	}

	@Column(name = "Code")
	public Serializable getCode() {
		return this.code;
	}

	public void setCode(Serializable code) {
		this.code = code;
	}

	@Column(name = "QuestionContent")
	public Serializable getQuestionContent() {
		return this.questionContent;
	}

	public void setQuestionContent(Serializable questionContent) {
		this.questionContent = questionContent;
	}

	@Column(name = "Option1Name")
	public Serializable getOption1name() {
		return this.option1name;
	}

	public void setOption1name(Serializable option1name) {
		this.option1name = option1name;
	}

	@Column(name = "Option2Name")
	public Serializable getOption2name() {
		return this.option2name;
	}

	public void setOption2name(Serializable option2name) {
		this.option2name = option2name;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
