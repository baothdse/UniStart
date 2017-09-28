package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.repositories.BlockRepository;

@Service
@Transactional
public class BlockService {
	
	@Autowired
	private BlockRepository blockRepo;

}
