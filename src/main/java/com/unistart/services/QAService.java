package com.unistart.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.QuestionAnswer;
import com.unistart.repositories.QARepository;
import com.unistart.services.interfaces.QAInterface;
import com.unistart.services.interfaces.UserServiceInterface;

@Service
@Transactional
public class QAService implements QAInterface {

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private QARepository qaRepository;

	@Override
	public boolean saveQa(String title, String contents, int type, int parentId, int userId) {
		// TODO Auto-generated method stub
		QuestionAnswer qa = new QuestionAnswer();
		Calendar cal = Calendar.getInstance();
		if (type == 1) {
			if (title != "") {
				qa.setTitle(title);
				qa.setContent(contents);
				qa.setParentId(0);
				qa.setCount(0);
				qa.setIsActive(true);
				qa.setType(type);
				qa.setUsers(userService.getUserById(userId));
				qa.setCreatedDateTime(cal.getTime());
				qaRepository.save(qa);
				return true;
			}
			return false;
		} else if (type == 2) {
			int count = qaRepository.getCountByQaId(parentId).getCount() + 1;
			qaRepository.updateCount(count, parentId);
			qa.setTitle("");
			qa.setCount(0);
			qa.setContent(contents);
			qa.setParentId(parentId);
			qa.setIsActive(true);
			qa.setType(type);
			qa.setUsers(userService.getUserById(userId));
			qa.setCreatedDateTime(cal.getTime());
			qaRepository.save(qa);
			return true;
		}
		return false;
	}

	@Override
	public QuestionAnswer getQaById(int qaId, int userId) {
		// TODO Auto-generated method stub
		QuestionAnswer qa = qaRepository.findById(qaId);
		if (qa.getUsers().getId() == userId) {
			qaRepository.updateCount(0, qaId);
		}
		return qa;
	}

	@Override
	public List<QuestionAnswer> getAnswerOfQuestion(int questionId) {
		// TODO Auto-generated method stub
		return qaRepository.findByParentId(questionId, 2);
	}

	@Override
	public List<QuestionAnswer> getAllQuestion() {
		// TODO Auto-generated method stub
		return qaRepository.findAllQuestion();
	}

	@Override
	public List<QuestionAnswer> getAllQuestionByUserId(int userId) {
		// TODO Auto-generated method stub
		return qaRepository.findAllQuestionByUserId(userId);
	}

}
