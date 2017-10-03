package com.unistart.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.University;
import com.unistart.entities.customentities.UniversityPoint;
import com.unistart.repositories.ReviewRepository;
import com.unistart.services.interfaces.ReviewServiceInterface;
import com.unistart.services.interfaces.UniversityServiceInterface;

@Service
@Transactional
public class ReviewService implements ReviewServiceInterface {

	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private UniversityServiceInterface uniService;

	private UniversityPoint universityPoint;
	private List<UniversityPoint> listUniversityPoint;

	private Double starCare;
	private Double starTeaching;
	private Double starSocieties;
	private Double starFacilities;
	private Double starCareer;
	private Double recommentPoint;

	@Override
	public int countStarCare(int universityId) {
		// TODO Auto-generated method stub
		int count = reviewRepo.countStarCare(universityId);
		return count;
	}

	@Override
	public int countStarTeaching(int universityId) {
		// TODO Auto-generated method stub
		int count = reviewRepo.countStarTeaching(universityId);
		return count;
	}

	@Override
	public int countStarFacilities(int universityId) {
		// TODO Auto-generated method stub
		int count = reviewRepo.countStarFacilites(universityId);
		return count;
	}

	@Override
	public int countStarSocieties(int universityId) {
		// TODO Auto-generated method stub
		int count = reviewRepo.countStarSocieties(universityId);
		return count;
	}

	@Override
	public int countStarCareer(int universityId) {
		// TODO Auto-generated method stub
		int count = reviewRepo.countStarCareer(universityId);
		return count;
	}

	@Override
	public void calculateStarTeaching(int universityId) {
		// TODO Auto-generated method stub

		int totalTeachingReview = countStarTeaching(universityId);
		int totalPoint = 0;
		List<Integer> listOfTeachingPoint = reviewRepo.getStarTeaching(universityId);
		for (int index = 0; index < listOfTeachingPoint.size(); index++) {
			totalPoint += listOfTeachingPoint.get(index);
		}
		double average = (double) totalPoint / totalTeachingReview;
		setStarTeaching(average);
	}

	@Override
	public void calculateStarFacilities(int universityId) {
		// TODO Auto-generated method stub
		int totalFacilitiesReview = countStarFacilities(universityId);
		int totalPoint = 0;
		List<Integer> listOfFacilitiesPoint = reviewRepo.getStarFacilities(universityId);
		for (int index = 0; index < listOfFacilitiesPoint.size(); index++) {
			totalPoint += listOfFacilitiesPoint.get(index);
		}
		double average = (double) totalPoint / totalFacilitiesReview;
		setStarFacilities(average);
	}

	@Override
	public void calculateStarSocieties(int universityId) {
		// TODO Auto-generated method stub
		int totalSocietiesReview = countStarSocieties(universityId);
		int totalPoint = 0;
		List<Integer> listOfSocietiesPoint = reviewRepo.getStarSocieties(universityId);
		for (int index = 0; index < listOfSocietiesPoint.size(); index++) {
			totalPoint += listOfSocietiesPoint.get(index);
		}
		double average = (double) totalPoint / totalSocietiesReview;
		setStarSocieties(average);
	}

	@Override
	public void calculateStarCare(int universityId) {
		// TODO Auto-generated method stub
		int totalCareReview = countStarCare(universityId);
		int totalPoint = 0;
		List<Integer> listOfCarePoint = reviewRepo.getStarCare(universityId);
		for (int index = 0; index < listOfCarePoint.size(); index++) {
			totalPoint += listOfCarePoint.get(index);
		}
		double average = (double) totalPoint / totalCareReview;
		setStarCare(average);
	}

	@Override
	public void calculateStarCareer(int universityId) {
		// TODO Auto-generated method stub
		int totalCareerReview = countStarCareer(universityId);
		int totalPoint = 0;
		List<Integer> listOfCareerPoint = reviewRepo.getStarCareer(universityId);
		for (int index = 0; index < listOfCareerPoint.size(); index++) {
			totalPoint += listOfCareerPoint.get(index);
		}
		double average = (double) totalPoint / totalCareerReview;
		setStarCareer(average);
	}

	@Override
	@PostConstruct
	@Scheduled(cron = "0 0 2 * * *")
	public void calculateTotalAverage() {
		List<University> listId = uniService.getListId();
		List<UniversityPoint> listPoint = new ArrayList<UniversityPoint>();
		UniversityPoint point = null;
		for (int index = 0; index < listId.size(); index++) {
			
			calculateStarTeaching(listId.get(index).getId());
			calculateStarFacilities(listId.get(index).getId());
			calculateStarSocieties(listId.get(index).getId());
			calculateStarCare(listId.get(index).getId());
			calculateStarCareer(listId.get(index).getId());
			calculateRecomment(listId.get(index).getId());
			if(getRecommentPoint() == null) {
				point = new UniversityPoint(listId.get(index).getId(), getStarCare(), 
					getStarTeaching(), getStarSocieties(), getStarFacilities(),getStarCareer());
			} else {
				point = new UniversityPoint(listId.get(index).getId(), getStarCare(), 
						getStarTeaching(), getStarSocieties(), getStarFacilities(),getStarCareer(), getRecommentPoint());
			}
			
			listPoint.add(point);
		}
		setListUniversityPoint(listPoint);
	}
	public UniversityPoint getUniversityPoint() {

		return universityPoint;
	}

	public void setUniversityPoint(UniversityPoint universityPoint) {
		this.universityPoint = universityPoint;
	}

	public Double getStarCare() {
		return starCare;
	}

	public void setStarCare(Double starCare) {
		this.starCare = starCare;
	}

	public Double getStarTeaching() {
		return starTeaching;
	}

	public void setStarTeaching(Double starTeaching) {
		this.starTeaching = starTeaching;
	}

	public Double getStarSocieties() {
		return starSocieties;
	}

	public void setStarSocieties(Double starSocieties) {
		this.starSocieties = starSocieties;
	}

	public Double getStarFacilities() {
		return starFacilities;
	}

	public void setStarFacilities(Double starFacilities) {
		this.starFacilities = starFacilities;
	}

	public Double getStarCareer() {
		return starCareer;
	}

	public void setStarCareer(Double starCareer) {
		this.starCareer = starCareer;
	}
	
	@Override
	public List<UniversityPoint> getListUniversityPoint() {
		return listUniversityPoint;
	}

	public void setListUniversityPoint(List<UniversityPoint> listUniversityPoint) {
		this.listUniversityPoint = listUniversityPoint;
	}

	@Override
	public UniversityPoint getPointById(int universityId) {
		// TODO Auto-generated method stub
		UniversityPoint uniPoint = null;
		for (int index = 0; index < listUniversityPoint.size(); index++) {
			if(universityId == listUniversityPoint.get(index).getUniversityId()) {
				uniPoint = listUniversityPoint.get(index);
			}
		}
		return uniPoint;
	}

	public Double getRecommentPoint() {
		return recommentPoint;
	}

	public void setRecommentPoint(Double recommentPoint) {
		this.recommentPoint = recommentPoint;
	}

	@Override
	public int countRecomment(int universityId) {
		// TODO Auto-generated method stub
		return reviewRepo.countRecommended(universityId);
	}

	@Override
	public void calculateRecomment(int universityId) {
		// TODO Auto-generated method stub
		int totalRecomment = reviewRepo.countTotalRecomment(universityId);
		int trueComment = countRecomment(universityId);
		System.out.println(totalRecomment);
		System.out.println(trueComment);
		if (totalRecomment != 0) {
			double averagePercent =  ((double)(trueComment)/totalRecomment) * 100;
			System.out.println(averagePercent); 
			setRecommentPoint(averagePercent); 
		}else {
			setRecommentPoint(null);
		}
	}

	
}
