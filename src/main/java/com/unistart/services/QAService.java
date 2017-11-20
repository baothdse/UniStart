package com.unistart.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.ArticleTag;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.QuestionAnswer;
import com.unistart.entities.QuestionTag;
import com.unistart.entities.Report;
import com.unistart.entities.Tag;
import com.unistart.entities.University;
import com.unistart.entities.Vote;
import com.unistart.repositories.QARepository;
import com.unistart.repositories.QATagRepository;
import com.unistart.repositories.ReportRespository;
import com.unistart.repositories.TagRepository;
import com.unistart.repositories.UniversityRepository;
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
	@Autowired
	private UniversityRepository uniRepo;
	@Autowired
	private QATagRepository qaTagRepo;
	@Autowired
	private TagRepository tagRepo;
	@Autowired
	private ReportRespository reportRepo;
	
	
	@Override
	public Integer saveQa(String title, String contents, int type, int parentId, int userId, int[] tags) {
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
				qa.setStatus(false);
				QuestionAnswer newQa = qaRepository.save(qa);
				if(saveTag(newQa.getId(), tags)){
					return newQa.getId();
				}
				return 0;
			}
			return 0;
		} else if (type == 2) {
			QuestionAnswer ques = qaRepository.findById(parentId);
			if(ques.getUsers().getId() != userId){
				int count = qaRepository.getCountByQaId(parentId).getCount() + 1;
				qaRepository.updateCount(count, parentId);
			}
			qa.setTitle("");
			qa.setCount(0);
			qa.setContent(contents);
			qa.setParentId(parentId);
			qa.setIsActive(true);
			qa.setType(type);
			qa.setUsers(userService.getUserById(userId));
			qa.setCreatedDateTime(cal.getTime());
			qa.setLastUpdatedTime(cal.getTime());
			qa.setStatus(true);
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
		Report report = new Report();
		for(int i = 0;i<list.size();i++){
			vote = voteRepo.findByUserAndAnswer(userId, list.get(i).getId());
				if(vote != null){
					list.get(i).setVoteByUser(true);
				}
			report = reportRepo.findByUserAndAnswer(userId, list.get(i).getId());
			if(report != null){
				list.get(i).setReportByUser(true);
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
		
		List<QuestionAnswer> list = qaRepository.findAllQuestionByUserId(userId);
		for(int i = 0; i<list.size();i++){
			int count = getTotalAnswerOfQuestion(list.get(i).getId());
			list.get(i).setTotalAnswer(count);
		}
		return list;
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
	public boolean changeStatusQuestionAnswer(int qaId, boolean status, boolean isActive) {
    	QuestionAnswer qa = qaRepository.findById(qaId);
		if (qa != null){
			qa.setStatus(status);
			qa.setIsActive(isActive);
			qaRepository.save(qa);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveTag(int id, int[] tagUni) {
		QuestionAnswer qa = qaRepository.findById(id);
		if(qa != null){
			for(int i =0; i<tagUni.length;i++){
				QuestionTag qT = new QuestionTag();
				qT.setQa(qa);
				Tag tag = tagRepo.findById(tagUni[i]);
				qT.setTag(tag);
				qaTagRepo.save(qT);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTag(int questionId, int[] tagUni) {
		QuestionAnswer qa = qaRepository.findById(questionId);
		if(qa != null){
			List<QuestionTag> questionTag = qaTagRepo.findByQuestionId(questionId);
			QuestionTag qT = new QuestionTag();
			ArrayList<Integer> test = new ArrayList<Integer>();
			for(int x=0;x<tagUni.length;x++){
				test.add(tagUni[x]);
			}
			for(int a=0; a<questionTag.size();a++){
			    if(test.contains(questionTag.get(a).getTag().getId())==false){
			    	qT = qaTagRepo.findByqaIdAnduniId(questionId, questionTag.get(a).getTag().getId());
			    	qaTagRepo.deleteTag(qT.getId());
			    }
			}
			for(int i=0; i<tagUni.length;i++){
				qT = qaTagRepo.findByqaIdAnduniId(questionId, tagUni[i]);
				if(qT == null){
					qT = new QuestionTag();
					qT.setQa(qa);
					Tag tag = tagRepo.findById(tagUni[i]);
					qT.setTag(tag);
					qaTagRepo.save(qT);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<QuestionTag> getTagOfQuestion(int qaId) {
		List<QuestionTag> listTag = qaTagRepo.getTagByquestionId(qaId);
		return listTag;
	}

	@Override
	public void updateTotalReport(int qaId) {
		// TODO Auto-generated method stub
		QuestionAnswer qa = getQaByQaId(qaId);
		int totalReport = qa.getNumberOfReport() + 1;
		qaRepository.setTotalReport(totalReport, qaId);
	}

	@Override
	public List<QuestionAnswer> listAllQuestionNeedAccept() {
		return qaRepository.findAllQuestionNeedAccept();
	}

	@Override
	public int numberOfQuestionNeedAccept() {
		return qaRepository.numberOfQuestionNeedAccept();
	}

	@Override
	public List<QuestionAnswer> getAllReport() {
		List<QuestionAnswer> list = qaRepository.findByStatusAndIsActiveAndNumberOfReportGreaterThan(true,true,0);
		return list;
	}

	@Override
	public boolean changeReportStatus(int qaId, boolean status, boolean isActive) {
		QuestionAnswer qa = qaRepository.findById(qaId);
		if(qa != null){
			if(status == true && isActive == true){
				qa.setNumberOfReport(0);
				qaRepository.save(qa);
				return true;
			}else if(status == false && isActive == false){
				qa.setStatus(status);
				qa.setIsActive(isActive);
				qaRepository.save(qa);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Tag> getAllTag() {
		return tagRepo.findAll();
	}

	@Override
	public int numberOfReport() {
		return qaRepository.numberOfReport();
	}

}
