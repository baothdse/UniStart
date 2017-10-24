package com.unistart.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;
import com.unistart.entities.customentities.Correlate;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.CorrelateServiceInterface;

@Service
@Transactional
public class CorrelateService implements CorrelateServiceInterface{
       
	@Autowired
	private MajorUniRepository majorUniRepo;
	
	@Autowired
	private UniversityRepository uniRepo;
	
	List<Correlate> listCorrelate;
	
	public List<Correlate> countSameMajor(int unversityId){
		listCorrelate = new ArrayList<Correlate>();
		int majorIdOfSelectedUni = 0, majorIdOfUni = 0;
		List<MajorUniversity> listMajorUni = majorUniRepo.findByUniId(unversityId);
		List<MajorUniversity> university = majorUniRepo.getListUni(unversityId);
		for(int i=0;i<university.size();i++){
			Correlate co = new Correlate();
			int count = 0;
			List<MajorUniversity> list = majorUniRepo.findByUniId(university.get(i).getUniversity().getId());
			for(int j=0;j<listMajorUni.size();j++){
				majorIdOfSelectedUni = listMajorUni.get(j).getMajor().getId();
				for(int x=0;x<list.size();x++){
					majorIdOfUni = list.get(x).getMajor().getId();
					if(majorIdOfSelectedUni == majorIdOfUni){
						count = count +1;
					}
				}
			}
			co.setNumberOfSameMajor(count);
			co.setUniversityId(university.get(i).getUniversity().getId());
			listCorrelate.add(co);
		}
		return listCorrelate;
	}
	
	@Override
	public List<Correlate> getTop10Uni(int universityId){
		listCorrelate = countSameMajor(universityId);
		Collections.sort(listCorrelate, new Correlate());
		return listCorrelate.subList(0, listCorrelate.size()>=10 ? 10 : listCorrelate.size());
		
	}
}
