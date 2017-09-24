package com.unistart.entities;
// Generated Sep 25, 2017 1:40:32 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Provider generated by hbm2java
 */
@Entity
@Table(name = "Provider", schema = "dbo", catalog = "University")
public class Provider implements java.io.Serializable {

	private String providerId;
	private Users users;
	private String providerName;

	public Provider() {
	}

	public Provider(String providerId, String providerName) {
		this.providerId = providerId;
		this.providerName = providerName;
	}

	public Provider(String providerId, Users users, String providerName) {
		this.providerId = providerId;
		this.users = users;
		this.providerName = providerName;
	}

	@Id

	@Column(name = "ProviderID", unique = true, nullable = false)
	public String getProviderId() {
		return this.providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "ProviderName", nullable = false)
	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

}
