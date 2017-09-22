package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Major;
import com.unistart.repositories.MajorRepository;
import com.unistart.services.interfaces.MajorServiceInterface;

@Service
@Transactional
public class MajorService implements MajorServiceInterface {

	@Autowired
	private MajorRepository majorRepository;
	
	private List<Major> listMajor;
	@Override
	public List<Major> listAllMajorName() {
		listMajor = majorRepository.showByMajorName();
		return listMajor;
	}
}
