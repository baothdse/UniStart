package com.unistart.services.interfaces;

import com.unistart.entities.Mbtiresult;

public interface MBTIResultServiceInterface {

	Mbtiresult findById(int id);

	boolean saveMbtiResult(String mbtiName, int user);
}
