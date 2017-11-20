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
import com.unistart.constant.ErrorConstant;
import com.unistart.constant.ParamConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.ArticleTag;
import com.unistart.entities.QuestionAnswer;
import com.unistart.entities.QuestionTag;
import com.unistart.entities.Review;
import com.unistart.entities.Tag;
import com.unistart.entities.University;
import com.unistart.error.ErrorNotification;
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
		int isqaId = qaService.saveQa(title, contents, type, parentId, userId, qa.getTagUniversity());
		if (isqaId != 0) {
			return new ResponseEntity<Integer> (isqaId, HttpStatus.OK);
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
		//boolean isUpdate = qaService.updateTag(qaId, qa.getTagUniversity());
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

//	@RequestMapping(value = UrlConstant.CHANGE_QUESTION_STATUS, method = RequestMethod.POST)
//	public ResponseEntity<?> deleteQuestionAnswer (@RequestBody QuestionAnswer qa) {
//		int qaId = qa.getId();
//		boolean status = qa.getStatus();
//		boolean isActive = qa.getIsActive();
//		boolean isDelete = qaService.changeStatusQuestionAnswer(qaId,status,isActive);
//		return new ResponseEntity<Boolean> (isDelete, HttpStatus.OK);
//	}
	
	@RequestMapping(value = UrlConstant.GET_TAG_QUESTION, method = RequestMethod.GET)
	public ResponseEntity<?> getTagOfArticle(@RequestParam(value = "qaId") int qaId){
		List<QuestionTag> listTag = qaService.getTagOfQuestion(qaId);
		return new ResponseEntity<List<QuestionTag>>(listTag, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.QUESTION_NEED_ACCEPT, method = RequestMethod.GET)
	public ResponseEntity<?> listAllNeedAcceptReview(){
		List<QuestionAnswer> listAllQuestion = qaService.listAllQuestionNeedAccept();
		if(listAllQuestion != null){
			return new ResponseEntity<List<QuestionAnswer>>(listAllQuestion, HttpStatus.OK);
		}else {
			ErrorNotification error = new ErrorNotification(ErrorConstant.MES017);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
		
	}
	
	@RequestMapping(value = UrlConstant.NUMBER_QUESTION_NEED_ACCEPT, method = RequestMethod.GET)
	public ResponseEntity<?> getNumberQuestionNeedAccept(){
		int numberOfQuestion = qaService.numberOfQuestionNeedAccept();
		return new ResponseEntity<Integer>(numberOfQuestion, HttpStatus.OK);	
	}

	@RequestMapping(value = UrlConstant.CHANGE_QUESTION_STATUS, method = RequestMethod.POST)
	public ResponseEntity<?> changeQuestionStatus(@RequestBody QuestionAnswer qa){
		int id = qa.getId();
		boolean status = qa.getStatus();
		boolean isActive = qa.getIsActive();
		boolean isSuccess = qaService.changeStatusQuestionAnswer(id, status, isActive);
		return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
//		int qaId = qa.getId();
//		boolean isDelete = qaService.deleteQuestionAnswer(qaId);
//		return new ResponseEntity<Boolean> (isDelete, HttpStatus.OK);
	}
	
//	@RequestMapping(value = UrlConstant.SET_REPORT, method = RequestMethod.POST)
//	public ResponseEntity<?> getTagOfArticle(@RequestParam(value = "qaId") int qaId){
//		List<QuestionTag> listTag = qaService.getTagOfQuestion(qaId);
//		return new ResponseEntity<List<QuestionTag>>(listTag, HttpStatus.OK);
//	}
	
	@RequestMapping(value = UrlConstant.GET_ALL_REPORT, method = RequestMethod.GET)
	public ResponseEntity<?> getAllReport(){
		List<QuestionAnswer> list = qaService.getAllReport();
		return new ResponseEntity<List<QuestionAnswer>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.CHANGE_REPORT_STATUS, method = RequestMethod.POST)
	public ResponseEntity<?> changeReportStatus(@RequestBody QuestionAnswer qa){
		int id = qa.getId();
		boolean status = qa.getStatus();
		boolean isActive = qa.getIsActive();
		boolean isSuccess = qaService.changeReportStatus(id, status, isActive);
		return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.GET_ALL_TAG, method = RequestMethod.GET)
	public ResponseEntity<?> getAllTag(){
		List<Tag> list = qaService.getAllTag();
		return new ResponseEntity<List<Tag>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.NUMBER_REPORT, method = RequestMethod.GET)
	public ResponseEntity<?> numberOfReport(){
		int number = qaService.numberOfReport();
		return new ResponseEntity<Integer>(number, HttpStatus.OK);
	}
}
