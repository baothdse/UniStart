package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Block;
import com.unistart.repositories.BlockRepository;
import com.unistart.services.interfaces.BlockServiceInterface;

@Service
@Transactional
public class BlockService implements BlockServiceInterface{
	
	@Autowired
	private BlockRepository blockRepo;

	@Override
	public List<Block> listAllBlock() {
		return blockRepo.getListOfBlock();
	}

	@Override
	public Block findByBlockName(String blockName) {
		return blockRepo.findByBlockName(blockName);
	}

}
