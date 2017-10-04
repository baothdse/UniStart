package com.unistart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Review;
import com.unistart.entities.University;
import com.unistart.entities.Users;
import com.unistart.repositories.ReviewRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.ReviewServiceInterface;

@Service
@Transactional
public class ReviewService implements ReviewServiceInterface{

	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private UserRepository userRepo;
	
	private University university;
	private Review review;
	private Users user;
	private List<Review> listALLReview;
	
	@Override
	public boolean saveReview(int universityId, int userId, String description, int starTeaching, int starFacilities, int starCare,
			 int starSocieties, int starCareer, boolean isRecomment, boolean status) {
		university = universityRepo.findById(universityId);
		user = userRepo.findById(userId);
		if (university != null && user != null) {
			review = new Review();
			review.setUniversity(university);
			review.setUsers(user);
			review.setDescription(description);
			review.setStarTeaching(starTeaching);
			review.setStarFacilities(starFacilities);
			review.setStarCare(starCare);
			review.setStarSocieties(starSocieties);
			review.setStarCareer(starCareer);
			review.setIsRecomment(isRecomment);
			review.setStatus(status);
			review.setIsActive(true);
			reviewRepo.save(review);
			return true;
		}
		return false;
	}

	@Override
	public List<Review> listReviewOfUniversity(int universityId) {
		listALLReview = reviewRepo.showReviewByUniversityId(universityId);
		return listALLReview;
	}
	public boolean changeReviewStatus(int id, boolean status, boolean isActive) {	
		review = reviewRepo.findById(id);
		if (review != null){
			review.setStatus(status);
			review.setIsActive(isActive);
			reviewRepo.save(review);
			return true;
		}
		return false;
	}

	List<Review> listAllReview;
	@Override
	public List<Review> listAllNeedAcceptReview() {
		listAllReview = reviewRepo.findNeedAcceptReview();
		return listAllReview;
	}
}
