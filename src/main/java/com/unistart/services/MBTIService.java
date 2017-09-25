package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Mbtiquestion;
import com.unistart.repositories.MBTIRepository;
import com.unistart.services.interfaces.MBTIServiceInterface;

@Service
@Transactional
public class MBTIService implements MBTIServiceInterface {

	@Autowired
	private MBTIRepository mbtiRepository;
	private List<Mbtiquestion> listMbtiquestion;
	
	@Override
	public List<Mbtiquestion> listAllMbtiquestion() {
		listMbtiquestion = mbtiRepository.showByMBTIGroup();
		return listMbtiquestion;
	}
	
	

}
