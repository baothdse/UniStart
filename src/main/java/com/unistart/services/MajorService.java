package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	private List<MajorUniversity> listMajorUni;
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
		majorUni.setIsActive(true);
		majorUniRepo.save(majorUni);
		return true;
	}
	@Override
	public Major getMajorById(int id) {
		return majorRepository.findById(id);
	}
	@Override
	public List<MajorUniversity> getByUniversityId(int uniId){
		listMajorUni = majorUniRepo.getByUniversityId(uniId);
		return listMajorUni;
	}
	
	@Override
	public List<MajorUniversity> getUniverityWithMajor(University uni){
		return majorUniRepo.findByUniversity(uni);
	}
	@Override
	public MajorUniversity findByMajorIdAndUniId(int majorId, int uniId) {
		return majorUniRepo.findByMajorIdAndUniId(majorId, uniId);
	}
	@Override
	public boolean changeActive(int majorUniId, boolean isActive) {
		majorUniRepo.changeIsActive(majorUniId, isActive);
		return true;
	}
	@Override
	public boolean saveMajorUniDetail(int id, double year, String des, String requirement, String pros) {
		MajorUniversity majorUni = majorUniRepo.findById(id);
		if(majorUni != null){
			majorUniRepo.updateMajorUni(id, year, des, requirement, pros);
			return true;
		}
		return false;
	}
	
	@Override
	public List<University> getByMajor(int majorId) {
		// TODO Auto-generated method stub
		List<University> u = majorUniRepo.getByMajor(majorId);
		return u.subList(0, u.size() >= 3 ? 3: u.size());
	}
	@Override
	public List<MajorUniversity> getListId() {
		return majorUniRepo.getListId();
	}
	@Override
	public List<MajorUniversity> getForTag(int uniId) {
		return majorUniRepo.findByIdForTag(uniId);
	}
	
}
