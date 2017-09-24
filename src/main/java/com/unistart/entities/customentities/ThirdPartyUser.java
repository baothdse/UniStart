package com.unistart.entities.customentities;

import java.io.Serializable;

import com.unistart.entities.Provider;

public class ThirdPartyUser implements Serializable {
	private String email;
	private String image;
	private String name;
	private int providerId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

}
