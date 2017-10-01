package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.BlockMajorUniversity;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.ScoreHistory;
import com.unistart.entities.customentities.LocationUniversity;
import com.unistart.entities.customentities.MajorBlockScore;
import com.unistart.entities.customentities.MajorScore;
import com.unistart.error.ErrorNotification;
import com.unistart.repositories.BlockMajorUniRepository;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.services.interfaces.BlockMajorUniInterface;

@RestController
@RequestMapping(UrlConstant.SCORE)
public class ScoreController {
   @Autowired
   private BlockMajorUniInterface blockMajorUniService;
   private ErrorNotification error;
   private BlockMajorUniversity bmu;
   private ScoreHistory scoreHistory;
   
   @RequestMapping(value = UrlConstant.SAVE_SCORE, method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody MajorBlockScore majorScore) {
	   List<MajorScore> listMajorScore = majorScore.getMajorScore();
		MajorScore ms = new MajorScore();
		for (int i = 0; i < listMajorScore.size(); i++) {
			ms = listMajorScore.get(i);
			int blockId = ms.getBlockId();
			int majorUniId = ms.getMajorUniId();
			bmu = blockMajorUniService.findByBlockAndMajor(blockId, majorUniId);
			if (bmu == null) {
				// create blockMajorUni
				boolean isSave = blockMajorUniService.saveBlockOfMajor(blockId, majorUniId);
				if (isSave) {
					bmu = blockMajorUniService.findByBlockAndMajor(blockId, majorUniId);
					Double score = (double) ms.getScore();
					int year = ms.getYear();
					boolean isAddScore = blockMajorUniService.saveScore(bmu.getId(), score, year);
					if (isAddScore == false) {
						error = new ErrorNotification(ErrorConstant.MES011);
						return new ResponseEntity<ErrorNotification>(error, HttpStatus.CONFLICT);
					}
				} else {
					error = new ErrorNotification(ErrorConstant.MES010);
					return new ResponseEntity<ErrorNotification>(error, HttpStatus.NOT_FOUND);
				}
			} else {
				Double score = (double) ms.getScore();
				int year = ms.getYear();
				scoreHistory = blockMajorUniService.findByIdAndYear(bmu.getId(), year);
				if(scoreHistory == null){
					boolean isAddScore = blockMajorUniService.saveScore(bmu.getId(), score, year);
					if (isAddScore == false) {
						//add Score
						error = new ErrorNotification(ErrorConstant.MES011);
						return new ResponseEntity<ErrorNotification>(error, HttpStatus.CONFLICT);
					}
				}else{
					//update socre
					boolean isUpdateScore = blockMajorUniService.updateScore(bmu.getId(), score, year);
					if(isUpdateScore == false){
						error = new ErrorNotification(ErrorConstant.MES011);
						return new ResponseEntity<ErrorNotification>(error, HttpStatus.CONFLICT);
					}
				}
			}
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
