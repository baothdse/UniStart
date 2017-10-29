package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.MajorMbti;
import com.unistart.entities.Mbtiquestion;
import com.unistart.entities.Mbtitype;


public interface MBTIServiceInterface {

	List<Mbtiquestion> listAllMbtiquestion();
	
	Mbtitype getMBTIType (int mbtiID);
}
