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
@Table(name = "ArticleTag", schema = "dbo", catalog = "University")
public class ArticleTag {
		private Integer id;
		private Article article;
		private MajorUniversity majorUni;
		private String majorName;
		private int majorUniId;
		private int majorId;
		
		public ArticleTag() {
			super();
		}


		public ArticleTag(Integer id, Article article, MajorUniversity majorUni) {
			super();
			this.id = id;
			this.article = article;
			this.majorUni = majorUni;
		}

		public ArticleTag(Integer id, MajorUniversity majorUni) {
			super();
			this.id = id;
			this.majorId = majorUni.getMajor().getId();
			this.majorName = majorUni.getMajor().getMajorName();
			this.majorUniId = majorUni.getId();

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
		@JoinColumn(name = "ArticleId")
		public Article getArticle() {
			return article;
		}


		public void setArticle(Article article) {
			this.article = article;
		}

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "MajorUniId")
		public MajorUniversity getMajorUni() {
			return majorUni;
		}


		public void setMajorUni(MajorUniversity majorUni) {
			this.majorUni = majorUni;
		}

		
		
		
		
}
