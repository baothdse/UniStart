package com.unistart.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.QuestionAnswer;
import com.unistart.entities.Vote;
import com.unistart.repositories.QARepository;
import com.unistart.repositories.VoteRepository;
import com.unistart.services.interfaces.QAInterface;
import com.unistart.services.interfaces.UserServiceInterface;

@Service
@Transactional
public class QAService implements QAInterface {

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private QARepository qaRepository;
	
	@Autowired
	private VoteRepository voteRepo;

	@Override
	public Integer saveQa(String title, String contents, int type, int parentId, int userId) {
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
				qa.setLastUpdatedTime(cal.getTime());
				QuestionAnswer newQa = qaRepository.save(qa);
				return newQa.getId();
			}
			return 0;
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
			qa.setLastUpdatedTime(cal.getTime());
			QuestionAnswer newQa = qaRepository.save(qa);
			return newQa.getId();
		}
		return 0;
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
	public List<QuestionAnswer> getAnswerOfQuestion(int questionId,int userId) {
		// TODO Auto-generated method stub
		List<QuestionAnswer> list = qaRepository.findByParentId(questionId, 2);
		Vote vote = new Vote();
		for(int i = 0;i<list.size();i++){
			vote = voteRepo.findByUserAndAnswer(userId, list.get(i).getId());
				if(vote != null){
					list.get(i).setVoteByUser(true);
				}
		}
		return list;
	}

	@Override
	public List<QuestionAnswer> getAllQuestion() {
		// TODO Auto-generated method stub
		List<QuestionAnswer> list = qaRepository.findAllQuestion();
		for(int i = 0; i<list.size();i++){
			int count = getTotalAnswerOfQuestion(list.get(i).getId());
			list.get(i).setTotalAnswer(count);
		}
		return list;
	}

	@Override
	public List<QuestionAnswer> getAllQuestionByUserId(int userId) {
		// TODO Auto-generated method stub
		return qaRepository.findAllQuestionByUserId(userId);
	}

	@Override
	public QuestionAnswer getQaByQaId(int qaId) {
		// TODO Auto-generated method stub
		return qaRepository.findById(qaId);
	}

	@Override
	public void updateTotalVote(int qaId) {
		// TODO Auto-generated method stub
		QuestionAnswer qa = getQaByQaId(qaId);
		int totalVote = qa.getVote() + 1;
		qaRepository.setTotalVote(totalVote, qaId);
	}

	@Override
	public int getTotalAnswerOfQuestion(int questionId) {
		// TODO Auto-generated method stub
		return qaRepository.getTotalAnswerOfQuestion(questionId);
	}

	@Override
	public boolean updateQa(String title, String contents, int qaId, int userId) {
		// TODO Auto-generated method stu
		QuestionAnswer qa = qaRepository.findByIdAndUserId(qaId, userId);
		if(qa != null) {
			Calendar cal = Calendar.getInstance();
			Date lastUpdatedTime = cal.getTime();
			qaRepository.updateQa(title, contents, lastUpdatedTime, qaId, userId);
			return true;
		} else {
			return false;
		}
	}
    
    @Override
	public boolean deleteQuestionAnswer(int qaId) {
		qaRepository.changeIsActive(qaId);
		return true;
	}

}
