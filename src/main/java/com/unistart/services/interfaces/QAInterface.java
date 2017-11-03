package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.QuestionAnswer;

public interface QAInterface {
	boolean saveQa(String title, String contents, int type, int parentId, int userId);
	QuestionAnswer getQaById(int qaId, int userId);
	
	List<QuestionAnswer> getAnswerOfQuestion(int questionId);
	List<QuestionAnswer> getAllQuestion();
	List<QuestionAnswer> getAllQuestionByUserId(int userId);
	boolean deleteQuestionAnswer(int qaId);
}
