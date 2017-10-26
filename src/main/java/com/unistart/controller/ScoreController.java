package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.BlockMajorUniversity;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.ScoreHistory;
import com.unistart.entities.University;
import com.unistart.entities.customentities.LocationUniversity;
import com.unistart.entities.customentities.MajorBlockScore;
import com.unistart.entities.customentities.MajorScore;
import com.unistart.error.ErrorNotification;
import com.unistart.repositories.BlockMajorUniRepository;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.services.interfaces.BlockMajorUniInterface;
import com.unistart.services.interfaces.BlockServiceInterface;

@RestController
@RequestMapping(UrlConstant.SCORE)
public class ScoreController {
   @Autowired
   private BlockMajorUniInterface blockMajorUniService;
   
   @Autowired
   private BlockServiceInterface blockService;
   private ErrorNotification error;
   private BlockMajorUniversity bmu;
   private ScoreHistory scoreHistory;
   
   @RequestMapping(value = UrlConstant.SAVE_SCORE, method = RequestMethod.POST)
	public ResponseEntity<?> saveScore(@RequestBody MajorBlockScore majorScore) {
	   List<MajorScore> listMajorScore = majorScore.getMajorScore();
	   int majorUniId = majorScore.getMajorUniId();
		MajorScore ms = new MajorScore();
		int blockId = blockService.findByBlockName(majorScore.getBlockName()).getId();
		for (int i = 0; i < listMajorScore.size(); i++) {
			ms = listMajorScore.get(i);
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
				if(bmu.getIsActive() == false){
					bmu.setIsActive(true);
				}
				Double score = (double) ms.getScore();
				int year = ms.getYear();
				scoreHistory = blockMajorUniService.findByIdAndYear(bmu.getId(), year);
				if(scoreHistory == null){
					//add Score
					boolean isAddScore = blockMajorUniService.saveScore(bmu.getId(), score, year);
					if (isAddScore == false) {
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
		bmu = blockMajorUniService.findByBlockAndMajor(blockId, majorUniId);
		return new ResponseEntity<Integer>(bmu.getId(), HttpStatus.OK);
	}
   
//   @RequestMapping(value = UrlConstant.GET_SCORE, method = RequestMethod.GET)
//	public ResponseEntity<?> getScore(@RequestParam(value = "universityId") int uniId){
//		List<MajorUniversity> listMajorUni = blockMajorUniService.findByUniId(uniId);
//		return new ResponseEntity<List<MajorUniversity>>(listMajorUni, HttpStatus.OK);
//	}
   
 @RequestMapping(value = UrlConstant.DELETE_BLOCK_SCORE, method = RequestMethod.POST)
	public ResponseEntity<?> getScore(@RequestBody BlockMajorUniversity blockMajorUni){
	 	int id = blockMajorUni.getId();
	    Boolean isRemove = blockMajorUniService.deleteBlockScore(id);
		return new ResponseEntity<Boolean>(isRemove, HttpStatus.OK);
	}
}
