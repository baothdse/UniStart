package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;

public interface MajorServiceInterface {

	List<Major> listAllMajorName();
    Major getMajorById(int id);
	boolean saveMajorUniversity(Major major, University uni);
	boolean changeActive(int majorUniId, boolean isActive);
	List<MajorUniversity> getUniverityWithMajor(University uni);
	MajorUniversity findByMajorIdAndUniId(int majorId, int uniId);
}
