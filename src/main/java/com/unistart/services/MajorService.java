package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;
import com.unistart.repositories.MajorRepository;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.services.interfaces.MajorServiceInterface;

@Service
@Transactional
public class MajorService implements MajorServiceInterface {

	@Autowired
	private MajorRepository majorRepository;
	
	@Autowired
	private MajorUniRepository majorUniRepo;
	
	private List<Major> listMajor;
	@Override
	public List<Major> listAllMajorName() {
		listMajor = majorRepository.showByMajorName();
		return listMajor;
	}
	@Override
	public boolean saveMajorUniversity(Major major, University uni) {
		MajorUniversity majorUni = new MajorUniversity();
		majorUni.setMajor(major);
		majorUni.setUniversity(uni);
		majorUniRepo.save(majorUni);
		return true;
	}
	@Override
	public Major getMajorById(int id) {
		return majorRepository.findById(id);
	}
	
	@Override
	public List<MajorUniversity> getUniverityWithMajor(University uni){
		return majorUniRepo.findByUniversity(uni);
	}
	
}
