package com.unistart.services.interfaces;

import com.unistart.entities.Mbtiresult;

public interface MBTIResultServiceInterface {

	Mbtiresult saveMbtiResult(String mbtiName, int user);
	
	boolean updateMbtiResult(String mbtiName, int userid);
	
	Mbtiresult getMBTIResult(int userId);
}
