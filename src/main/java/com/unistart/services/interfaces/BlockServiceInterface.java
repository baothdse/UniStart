package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Block;
import com.unistart.entities.Location;

public interface BlockServiceInterface {
	List<Block> listAllBlock();
	
	Block findByBlockName(String blockName);
}
