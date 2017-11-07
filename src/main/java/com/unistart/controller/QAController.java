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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unistart.constant.ParamConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.QuestionAnswer;
import com.unistart.entities.University;
import com.unistart.services.interfaces.QAInterface;

@RestController
@RequestMapping(value = UrlConstant.QA)
public class QAController {
	@Autowired
	private QAInterface qaService;
	
	@RequestMapping(value = UrlConstant.SAVE, method = RequestMethod.POST)
	public ResponseEntity<?> saveQa (@RequestBody QuestionAnswer qa) {
		String title = qa.getTitle();
		String contents = qa.getContent();
		int type = qa.getType();
		int parentId = qa.getParentId();
		int userId = qa.getUsers().getId();
		int isSuccess = qaService.saveQa(title, contents, type, parentId, userId);
		if (isSuccess != 0) {
			return new ResponseEntity<Integer> (isSuccess, HttpStatus.OK);
		}
		return new ResponseEntity<String> ("Save error", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping(value = UrlConstant.VIEW, method = RequestMethod.GET)
	public ResponseEntity<?> viewQuestion (@RequestParam(value = ParamConstant.QA_ID) int qaId,
								@RequestParam(value = ParamConstant.USER_ID) int userId) {
		QuestionAnswer question = qaService.getQaById(qaId, userId);
		question.getUsers().setPassword("");
		return new ResponseEntity<QuestionAnswer> (question, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.QUESTIONS, method = RequestMethod.GET)
	public ResponseEntity<?> viewQuestions () {
		List<QuestionAnswer> questions = qaService.getAllQuestion();
		for (int i = 0; i < questions.size(); i++) {
			questions.get(i).getUsers().setPassword("");
		}
		return new ResponseEntity<List<QuestionAnswer>> (questions, HttpStatus.OK);
	}
	@RequestMapping(value = UrlConstant.QUESTIONS_BY_USER, method = RequestMethod.GET)
	public ResponseEntity<?> viewQuestionByUser (@RequestParam(value = ParamConstant.USER_ID) int userId) {
		List<QuestionAnswer> questions = qaService.getAllQuestionByUserId(userId);
		for (int i = 0; i < questions.size(); i++) {
			questions.get(i).getUsers().setPassword("");
		}
		return new ResponseEntity<List<QuestionAnswer>> (questions, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.COUNT_ANSWER, method = RequestMethod.GET)
	public ResponseEntity<?> getTotalAnswer(@RequestParam(ParamConstant.QA_ID) int qaId) {
		int count = qaService.getTotalAnswerOfQuestion(qaId);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);		
	}
	@RequestMapping(value = UrlConstant.UPDATE, method = RequestMethod.POST)
	public ResponseEntity<?> updateQa(@RequestBody QuestionAnswer qa) {
		String contents = qa.getContent();
		String title = qa.getTitle();
		int qaId = qa.getId();
		int userId = qa.getUsers().getId();
		boolean isSuccess = qaService.updateQa(title, contents, qaId, userId);
		if(isSuccess) {
			return new ResponseEntity<String>("Update successful", HttpStatus.OK);		
		}
		return new ResponseEntity<String>("Cannot update", HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = UrlConstant.ANSWER_BY_QUESTION, method = RequestMethod.GET)
	public ResponseEntity<?> viewAnswerOfQuestion (@RequestParam(value = ParamConstant.QA_ID) int qaId,
			@RequestParam(value = ParamConstant.USER_ID) int userId) {
		List<QuestionAnswer> answers = qaService.getAnswerOfQuestion(qaId,userId);
		for (int i = 0; i < answers.size(); i++) {
			answers.get(i).getUsers().setPassword("");
		}
		return new ResponseEntity<List<QuestionAnswer>> (answers, HttpStatus.OK);
	} 

	@RequestMapping(value = UrlConstant.DELETE_QUESTION_ANSWER, method = RequestMethod.POST)
	public ResponseEntity<?> deleteQuestionAnswer (@RequestBody QuestionAnswer qa) {
		int qaId = qa.getId();
		boolean isDelete = qaService.deleteQuestionAnswer(qaId);
		return new ResponseEntity<Boolean> (isDelete, HttpStatus.OK);
	}
	
}
