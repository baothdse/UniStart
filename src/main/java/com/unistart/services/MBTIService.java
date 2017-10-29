package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.MajorMbti;
import com.unistart.entities.Mbtiquestion;
import com.unistart.entities.Mbtitype;
import com.unistart.repositories.MBTIRepository;
import com.unistart.repositories.MBTITypeRepository;
import com.unistart.services.interfaces.MBTIServiceInterface;

@Service
@Transactional
public class MBTIService implements MBTIServiceInterface {

	@Autowired
	private MBTIRepository mbtiRepository;
	@Autowired
	private MBTITypeRepository mbtiTypeRepo;
	private List<Mbtiquestion> listMbtiquestion;
	private List<MajorMbti> listMajorMBTI;
	
	@Override
	public List<Mbtiquestion> listAllMbtiquestion() {
		listMbtiquestion = mbtiRepository.showByMBTIGroup();
		return listMbtiquestion;
	}

	@Override
	public Mbtitype getMBTIType(int mbtiID) {
		return mbtiTypeRepo.findById(mbtiID);
	}
	
}
