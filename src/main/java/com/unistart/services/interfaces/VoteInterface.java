package com.unistart.services.interfaces;

import com.unistart.entities.Vote;

public interface VoteInterface {
	Vote getVoteByAnswerAndUser(int qaId, int userId);
	boolean saveVote(int qaId, int userId);
	int getTotalVoteByAnswer(int qaId);
	Vote getVoteById(int id);
}
