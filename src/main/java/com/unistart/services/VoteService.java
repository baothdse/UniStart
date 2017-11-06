package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Vote;
import com.unistart.repositories.VoteRepository;
import com.unistart.services.interfaces.QAInterface;
import com.unistart.services.interfaces.UserServiceInterface;
import com.unistart.services.interfaces.VoteInterface;

@Service
@Transactional
public class VoteService implements VoteInterface {

	@Autowired
	private VoteRepository voteRepo;
	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private QAInterface qaService;
	@Override
	public Vote getVoteByAnswerAndUser(int qaId, int userId) {
		// TODO Auto-generated method stub
		return voteRepo.findByUserAndAnswer(userId, qaId);
	}

	@Override
	public boolean saveVote(int qaId, int userId) {
		// TODO Auto-generated method stub
		if(getVoteByAnswerAndUser(qaId, userId) == null) {
			Vote vote = new Vote();
			vote.setQuestionAnswer(qaService.getQaByQaId(qaId));
			vote.setUser(userService.getUserById(userId));
			qaService.updateTotalVote(qaId);
			voteRepo.save(vote);
			return true;
		}
		return false;
	}

	@Override
	public int getTotalVoteByAnswer(int qaId) {
		// TODO Auto-generated method stub
		return voteRepo.getTotalVoteOfAnswer(qaId);
	}

	@Override
	public Vote getVoteById(int id) {
		// TODO Auto-generated method stub
		return voteRepo.findById(id);
	}

}
