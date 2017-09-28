package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Location;
import com.unistart.entities.Mbtiresult;
import com.unistart.entities.Mbtitype;
import com.unistart.entities.University;
import com.unistart.entities.Users;
import com.unistart.repositories.MBTIResultRepository;
import com.unistart.repositories.MBTITypeRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.MBTIResultServiceInterface;
import com.unistart.services.interfaces.UserServiceInterface;

@Service
@Transactional
public class MBTIResultService implements MBTIResultServiceInterface {

	@Autowired
	private MBTITypeRepository mbtiTypeRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MBTIResultRepository mbtiResultRepo;

	private Mbtitype mbtiType;
	private Users user;
	private Mbtiresult mbtiResult;
	
	@Override
	public boolean saveMbtiResult(String mbtiTypeName, int userId) {
		mbtiType = mbtiTypeRepo.findByMbtitypeName(mbtiTypeName);
		user = userRepo.findById(userId);
		System.out.println("aa" + mbtiType);
		
		if (mbtiType != null) {
			mbtiResult = new Mbtiresult();
			mbtiResult.setMbtitype(mbtiType);
			mbtiResult.setUsers(user);
			mbtiResultRepo.save(mbtiResult);
			return true;
		}	
		return false;
	}

	@Override
	public Mbtiresult findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}