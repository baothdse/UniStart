package com.unistart.entities;
// Generated Oct 1, 2017 10:03:30 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mbtitype generated by hbm2java
 */
@Entity
@Table(name = "MBTIType", schema = "dbo", catalog = "University")
public class Mbtitype implements java.io.Serializable {

	private Integer id;
	private String mbtitypeName;
	private String description;
	private boolean isActive;
	private Set<Mbtiresult> mbtiresults = new HashSet<Mbtiresult>(0);
	private Set<MajorMbti> majorMbtis = new HashSet<MajorMbti>(0);

	public Mbtitype() {
	}

	public Mbtitype(Integer id, boolean isActive) {
		this.id = id;
		this.isActive = isActive;
	}

	public Mbtitype(Integer id, String mbtitypeName, String description, boolean isActive,
			Set<Mbtiresult> mbtiresults,Set<MajorMbti> majorMbtis) {
		this.id = id;
		this.mbtitypeName = mbtitypeName;
		this.description = description;
		this.isActive = isActive;
		this.mbtiresults = mbtiresults;
		this.majorMbtis = majorMbtis;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "MBTITypeName")
	public String getMbtitypeName() {
		return this.mbtitypeName;
	}

	public void setMbtitypeName(String mbtitypeName) {
		this.mbtitypeName = mbtitypeName;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "isActive", nullable = false)
	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mbtitype")
	public Set<Mbtiresult> getMbtiresults() {
		return this.mbtiresults;
	}

	public void setMbtiresults(Set<Mbtiresult> mbtiresults) {
		this.mbtiresults = mbtiresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mbtitype")
	public Set<MajorMbti> getMajorMbtis() {
		return this.majorMbtis;
	}

	public void setMajorMbtis(Set<MajorMbti> majorMbtis) {
		this.majorMbtis = majorMbtis;
	}
}
