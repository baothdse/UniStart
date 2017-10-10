package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Block;
import com.unistart.entities.BlockMajorUniversity;
import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.ScoreHistory;
import com.unistart.repositories.BlockMajorUniRepository;
import com.unistart.repositories.BlockRepository;
import com.unistart.repositories.MajorRepository;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.ScoreRepository;
import com.unistart.services.interfaces.BlockMajorUniInterface;

@Service
@Transactional
public class BlockMajorUniService implements BlockMajorUniInterface{

	@Autowired
	private MajorUniRepository majorUniRepository;
	@Autowired
	private BlockMajorUniRepository BlockMajorUniRepo;
	@Autowired
	private BlockRepository BlockRepo;
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Override
	public boolean saveBlockOfMajor(int blockId, int majorId) {
		Block block = BlockRepo.findById(blockId);
		MajorUniversity major = majorUniRepository.findById(majorId);
		if(block != null && major !=null){
			BlockMajorUniversity bmu = new BlockMajorUniversity();
			bmu.setBlock(block);
			bmu.setMajorUniversity(major);
			bmu.setIsActive(true);
			BlockMajorUniRepo.save(bmu);
			return true;
		}
		return false;	
	}
	@Override
	public boolean saveScore(int blockMajorUniId, Double score, int year) {
		BlockMajorUniversity bmu = BlockMajorUniRepo.findById(blockMajorUniId);
		if(bmu != null){
			ScoreHistory scoreHistory = new ScoreHistory();
			scoreHistory.setBlockMajorUniversity(bmu);
			scoreHistory.setScore(score);
			scoreHistory.setYear(year);
			scoreRepository.save(scoreHistory);
			return true;
		}
		return false;
	}
	@Override
	public BlockMajorUniversity findByBlockAndMajor(int blockId, int majorId) {
		return BlockMajorUniRepo.findByBlockAndMajor(blockId, majorId);
	}
	@Override
	public ScoreHistory findByIdAndYear(int blockMajorUniId, int year) {
		return scoreRepository.findByIdAndYear(blockMajorUniId, year);
	}
	@Override
	public boolean updateScore(int blockMajorUniId, Double score, int year) {
		scoreRepository.updatScore(blockMajorUniId, score, year);
		return true;
	}
//	@Override
//	public List<MajorUniversity> findByUniId(int uniId) {
//		return majorUniRepository.findByUniId(uniId);
//	}
	@Override
	public boolean deleteBlockScore(int blockMajorUniId) {
		BlockMajorUniRepo.changeIsActive(blockMajorUniId);
		return true;
	}
	
	
	

}
