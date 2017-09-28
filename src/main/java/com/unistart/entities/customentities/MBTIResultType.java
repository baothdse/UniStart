package com.unistart.entities.customentities;

import java.io.Serializable;

import com.unistart.entities.Mbtiresult;
import com.unistart.entities.Mbtitype;
import com.unistart.entities.Users;

public class MBTIResultType implements Serializable{

	private Mbtiresult mbtiResult;
	private Mbtitype mbtiType;
	private Users user;
	
	public MBTIResultType() {
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Mbtiresult getMbtiResult() {
		return mbtiResult;
	}
	public void setMbtiResult(Mbtiresult mbtiResult) {
		this.mbtiResult = mbtiResult;
	}
	public Mbtitype getMbtiType() {
		return mbtiType;
	}
	public void setMbtiType(Mbtitype mbtiType) {
		this.mbtiType = mbtiType;
	}
	
	public MBTIResultType(Mbtiresult mbtiResult, Mbtitype mbtiType, Users user) {
		super();
		this.mbtiResult = mbtiResult;
		this.mbtiType = mbtiType;
		this.user = user;
	}

	
}
