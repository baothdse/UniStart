package com.unistart.entities;
// Generated Sep 21, 2017 4:14:36 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mbtiresult generated by hbm2java
 */
@Entity
@Table(name = "MBTIResult", schema = "dbo", catalog = "University")
public class Mbtiresult implements java.io.Serializable {

	private int id;
	private Mbtitype mbtitype;
	private Users users;

	public Mbtiresult() {
	}

	public Mbtiresult(int id) {
		this.id = id;
	}

	public Mbtiresult(int id, Mbtitype mbtitype, Users users) {
		this.id = id;
		this.mbtitype = mbtitype;
		this.users = users;
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
	@JoinColumn(name = "MBTITypeId")
	public Mbtitype getMbtitype() {
		return this.mbtitype;
	}

	public void setMbtitype(Mbtitype mbtitype) {
		this.mbtitype = mbtitype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
