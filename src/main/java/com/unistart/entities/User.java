package com.unistart.entities;
// Generated Sep 17, 2017 1:40:06 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "User", schema = "dbo", catalog = "University")
public class User implements java.io.Serializable {

	private int id;
	private Role role;
	private University university;
	private String username;
	private String password;
	private String name;
	private String avatar;
	private String email;
	private boolean isActive;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<Mbtiresult> mbtiresults = new HashSet<Mbtiresult>(0);
	private Set<Provider> providers = new HashSet<Provider>(0);

	public User() {
	}

	public User(int id, Role role, University university, String username, String password, boolean isActive) {
		this.id = id;
		this.role = role;
		this.university = university;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	public User(int id, Role role, University university, String username, String password, String name, String avatar,
			String email, boolean isActive, Set<Review> reviews, Set<Mbtiresult> mbtiresults, Set<Provider> providers) {
		this.id = id;
		this.role = role;
		this.university = university;
		this.username = username;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.email = email;
		this.isActive = isActive;
		this.reviews = reviews;
		this.mbtiresults = mbtiresults;
		this.providers = providers;
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
	@JoinColumn(name = "RoleID", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UniversityId", nullable = false)
	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Column(name = "Username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Avatar")
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IsActive", nullable = false)
	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Mbtiresult> getMbtiresults() {
		return this.mbtiresults;
	}

	public void setMbtiresults(Set<Mbtiresult> mbtiresults) {
		this.mbtiresults = mbtiresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Provider> getProviders() {
		return this.providers;
	}

	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}

}
