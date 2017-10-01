package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.University;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.BlockServiceInterface;
import com.unistart.entities.Block;

@RestController
@RequestMapping(UrlConstant.BLOCK)
public class BlockController {
	
	private ErrorNotification error;
	@Autowired
	private BlockServiceInterface blockService;
	private List<Block> listBlock;
	@RequestMapping(value = UrlConstant.GET_ALL_BLOCK, method = RequestMethod.GET)
	public ResponseEntity<?> getBlock() {
		listBlock = blockService.listAllBlock();
		return new ResponseEntity<List<Block>>(listBlock, HttpStatus.OK);
	}
}
