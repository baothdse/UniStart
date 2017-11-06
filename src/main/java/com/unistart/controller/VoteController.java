package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ParamConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Vote;
import com.unistart.services.interfaces.VoteInterface;

@RestController
@RequestMapping(value = UrlConstant.VOTE)
public class VoteController {
	
	@Autowired
	private VoteInterface voteService;
	
	@RequestMapping(value = UrlConstant.SAVE, method = RequestMethod.POST)
	public ResponseEntity<?> saveVote(@RequestBody Vote vote) {
		int qaId = vote.getQuestionAnswer().getId();
		int userId = vote.getUser().getId();
		boolean isSuccess = voteService.saveVote(qaId, userId);
		if(isSuccess) {
			return new ResponseEntity<String>("Save successful", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Cannot save", HttpStatus.CONFLICT);		
	}
	
//	@RequestMapping(value = UrlConstant.COUNT_VOTE, method = RequestMethod.GET)
//	public ResponseEntity<?> getTotalVote(@RequestParam(ParamConstant.QA_ID) int qaId) {
//		int count = voteService.getTotalVoteByAnswer(qaId);
//		return new ResponseEntity<Integer>(count, HttpStatus.OK);		
//	}
}
