package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.QuestionAnswer;

public interface QAInterface {
	boolean saveQa(String title, String contents, int type, int parentId, int userId);
	QuestionAnswer getQaById(int qaId, int userId);
	
	List<QuestionAnswer> getAnswerOfQuestion(int questionId);
	List<QuestionAnswer> getAllQuestion();
	List<QuestionAnswer> getAllQuestionByUserId(int userId); 
	
	QuestionAnswer getQaByQaId(int qaId);
	void updateTotalVote(int qaId);
	
	int getTotalAnswerOfQuestion(int questionId);
	
	boolean updateQa(String title, String contents, int qaId, int userId);
	boolean deleteQuestionAnswer(int qaId);
}
