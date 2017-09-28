package com.unistart.entities.customentities;

import com.unistart.entities.Role;

public class LoginUserInfo {
	private int userId;
	private String username;
	private String name;
	private Role role;
	private String image;
	private String email;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LoginUserInfo() {

	}
	
	public LoginUserInfo(int userId, String username, String name, Role role, String image, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.role = role;
		this.image = image;
		this.email = email;
	}
	
}
