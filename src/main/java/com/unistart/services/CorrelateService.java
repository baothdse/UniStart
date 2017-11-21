package com.unistart.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.BlockMajorUniversity;
import com.unistart.entities.Major;
import com.unistart.entities.MajorMbti;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.ScoreHistory;
import com.unistart.entities.University;
import com.unistart.entities.customentities.Correlate;
import com.unistart.entities.customentities.Pearson;
import com.unistart.entities.customentities.UniversityPoint;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.CorrelateServiceInterface;
import com.unistart.services.interfaces.ReviewServiceInterface;

@Service
@Transactional
public class CorrelateService implements CorrelateServiceInterface{
       
	@Autowired
	private MajorUniRepository majorUniRepo;
	
	@Autowired
	private UniversityRepository uniRepo;
	
	@Autowired
	private ReviewServiceInterface reviewService;
	List<Correlate> listCorrelate;
	
	// X is ogrin, Y...
	public List<Correlate> countSameMajor(int unversityId){
		listCorrelate = new ArrayList<Correlate>();
		int majorIdOfSelectedUni = 0, majorIdOfUni = 0;
		List<MajorUniversity> listMajorUni = majorUniRepo.findByUniId(unversityId);
		List<MajorUniversity> university = majorUniRepo.getListUni(unversityId);
		for(int i=0;i<university.size();i++){
			Correlate co = new Correlate();
			List<MajorUniversity> listMajorX = new ArrayList<MajorUniversity>();
			List<MajorUniversity> listMajorY = new ArrayList<MajorUniversity>();
			int count = 0;
			List<MajorUniversity> list = majorUniRepo.findByUniId(university.get(i).getUniversity().getId());
			for(int j=0;j<listMajorUni.size();j++){
				majorIdOfSelectedUni = listMajorUni.get(j).getMajor().getId();
				for(int x=0;x<list.size();x++){
					majorIdOfUni = list.get(x).getMajor().getId();
					if(majorIdOfSelectedUni == majorIdOfUni){
						count = count +1;
						listMajorX.add(list.get(x));
						listMajorY.add(listMajorUni.get(j));
					}
				}
			}
			co.setNumberOfSameMajor(count);
			co.setUniversityId(university.get(i).getUniversity().getId());
			co.setListMajorX(listMajorX);
			co.setListMajorY(listMajorY);
			listCorrelate.add(co);
		}
		return listCorrelate;
	}
	
	@Override
	public List<Pearson> getTop5Uni(List<Pearson> list){
		Collections.sort(list, new Pearson());
	    return list.subList(0, list.size()>=5 ? 5 : list.size());
		//return list;
	}
	
	public List<Correlate> getTop10Uni(int universityId){
		listCorrelate = countSameMajor(universityId);
		Collections.sort(listCorrelate, new Correlate());
	    return listCorrelate.subList(0, listCorrelate.size()>=10 ? 10 : listCorrelate.size());
		//return listCorrelate.subList(0, 1);
	}
	
	@Override
	public List<Pearson> count(int universityId){
		listCorrelate = getTop10Uni(universityId);
		University uni = uniRepo.findWithShortData(universityId);
		int locationId = uni.getLocation().getId();
		int trainSystem = uni.getTrainSystem().getId();
		List<Pearson> list = new ArrayList<>();
		Double n = caculateAvgSameMajor(listCorrelate);
		for(int i = 0 ;i<listCorrelate.size();i++){
			Pearson p = new Pearson();
			int uniId = listCorrelate.get(i).getUniversityId();
			System.out.println("uniid: " + uniId);
			University uniCompare = new University();
			uniCompare = uniRepo.findWithShortData(uniId);
			System.out.println(uniCompare);
			System.out.println(uniCompare.getId());
			int loId = uniCompare.getLocation().getId();
			int trainId = 0;
			if(uniCompare.getTrainSystem() != null && uniCompare.getTrainSystem().getId() != null){
				trainId = uniCompare.getTrainSystem().getId();
			}
			Double r = caculateNumerator(listCorrelate.get(i));
			Double ratio = listCorrelate.get(i).getNumberOfSameMajor()/n;
			if(ratio > 1){
				ratio = (double) 1;
			}
			int a = 0; int b = 0;
			if(locationId == loId){
				a = 1;
			}
			if(trainSystem == trainId){
				b = 1;
			}
			Double subtent = (double) 0;
			if(r.isNaN()){
				subtent = a*0.2 + b*0.2;
			}else{
				r = Math.abs(r);
				subtent = (r*ratio)*0.6 + a*0.2 + b*0.2;
			}
			p.setR(subtent);
			p.setUniversity(uniCompare);
			list.add(p);
		}
		return list;
	}
	
	public List<ScoreHistory> getListScore(int majorUniId){
		MajorUniversity majorUni = majorUniRepo.findById(majorUniId);
		List<BlockMajorUniversity> listBlockMajorUni = new ArrayList<>(majorUni.getBlockMajorUniversities());
		List<ScoreHistory> list = new ArrayList<>();
		for(int i = 0; i<listBlockMajorUni.size();i++){
			List<ScoreHistory> listScore = new ArrayList<>(listBlockMajorUni.get(i).getScoreHistories());
			for(int j=0; j<listScore.size();j++){
				if(listScore.get(j).getYear() == 2017){
					list.add(listScore.get(j));
				}
			}
		}
		return list;
	}
	public Double avgScoreOfBlock(MajorUniversity majorUni){
		Double totalScore = (double) 0;
		//MajorUniversity majorUni = majorUniRepo.findById(majorUniId);
		List<BlockMajorUniversity> listBlockMajorUni = new ArrayList<>(majorUni.getBlockMajorUniversities());
		for(int i = 0; i<listBlockMajorUni.size();i++){
			List<ScoreHistory> listScore = new ArrayList<>(listBlockMajorUni.get(i).getScoreHistories());
			for(int j=0; j<listScore.size();j++){
				if(listScore.get(j).getYear() == 2017){
					totalScore = totalScore + listScore.get(j).getScore();
				}
			}
		}
		Double avgScore = totalScore/listBlockMajorUni.size();
		return avgScore;
		
		
	}
	public Double caculateNumerator(Correlate compareUni){
		List<MajorUniversity> listMajorX = compareUni.getListMajorX();
		List<MajorUniversity> listMajorY = compareUni.getListMajorY();
		Double scoreX = (double) 0;
		Double scoreY = (double) 0;
		List<ScoreHistory> listScoreX = new ArrayList<>();
		List<ScoreHistory> listScoreY = new ArrayList<>();
		for(int i =0; i<listMajorX.size();i++){
			scoreX = scoreX + avgScoreOfBlock(listMajorX.get(i));
			scoreY = scoreY + avgScoreOfBlock(listMajorY.get(i));
		}
		// trung binh x
		Double avgX = scoreX/listMajorX.size();
		Double avgY = scoreY/listMajorY.size();
		Double numerator = (double) 0;
		Double xA = (double) 0;
		Double yA = (double) 0;
		for(int i =0; i<listMajorX.size();i++){
			Double x = (double) 0;
			Double y = (double) 0;
			List<BlockMajorUniversity> listX = new ArrayList<>(listMajorX.get(i).getBlockMajorUniversities());
				for(int j = 0; j<listX.size();j++){
					Double sumX = (double) 0;
					List<ScoreHistory> listScore = new ArrayList<>(listX.get(j).getScoreHistories());
						for(int a=0; a<listScore.size();a++){
							if(listScore.get(a).getYear() == 2017){
								sumX = sumX + listScore.get(a).getScore();
							}
						}
						x = sumX/listScore.size() - avgX;
				}
			xA = xA + x*x;
			List<BlockMajorUniversity> listY = new ArrayList<>(listMajorY.get(i).getBlockMajorUniversities());
			Double sumY = (double) 0;
				for(int j = 0; j<listY.size();j++){
					List<ScoreHistory> listScore = new ArrayList<>(listY.get(j).getScoreHistories());
						for(int a=0; a<listScore.size();a++){
							if(listScore.get(a).getYear() == 2017){
								sumY = sumY + listScore.get(a).getScore();
							}
						}
				}
				y = sumY/listY.size() - avgY;
			yA = yA + y*y;
			numerator = numerator + x*y;
		}
		Double r = numerator/Math.sqrt(xA*yA);
		return r;
	}
	
	public Double caculateAvgSameMajor(List<Correlate> listCorrelate){
		int total = 0;
		for(int i = 0; i<listCorrelate.size();i++){
			total = total + listCorrelate.get(i).getNumberOfSameMajor();
		}
		Double avg = (double) (total/listCorrelate.size());
		return avg;	
	}
	//End Correlate Uni
	
	//Top 5 Uni same Major MBTI
	public List<Correlate> countSameMBTI(List<MajorMbti> listMajorMBTI){
		List<Correlate> listPeason = new ArrayList<>();
		List<University> listUniId = uniRepo.getListId();
		for(int i = 0;i< listUniId.size();i++){
			System.out.println("uni: "+ listUniId.get(i).getId());
			Correlate pe = new Correlate();
			int count = 0;
			List<MajorUniversity> listMajorUni = majorUniRepo.findByUniIdShort(listUniId.get(i).getId());
			for(int j =0; j<listMajorMBTI.size();j++){
				int number1 = listMajorMBTI.get(j).getMajor().getId();
				for(int a =0; a<listMajorUni.size();a++){
					int number2 = listMajorUni.get(a).getMajor().getId();
					if(number1 == number2){
						count = count + 1;
						System.out.println("count: "+ count);
					}
				}
			}
			System.out.println("final: "+ count);
			pe.setNumberOfSameMajor(count);
			pe.setUniversityId(listUniId.get(i).getId());
			listPeason.add(pe);
		}
		return listPeason;
	}
	
	@Override
	public List<Correlate> getTop20Uni(List<MajorMbti> listMajorMBTI){
		listCorrelate = countSameMBTI(listMajorMBTI);
		Collections.sort(listCorrelate, new Correlate());
	    return listCorrelate.subList(0, listCorrelate.size()>=20 ? 20 : listCorrelate.size());
	}
	
	@Override
	public List<Pearson> getTop5UniMBTI(List<MajorMbti> listMajorMBTI){
		listCorrelate = getTop20Uni(listMajorMBTI);
		Double n = caculateAvgSameMajor(listCorrelate);
		System.out.println("n: " + n);
		List<Pearson> listPearson = new ArrayList<>();
		for(int i = 0; i<listCorrelate.size();i++){
			System.out.println(listCorrelate.get(i).getUniversityId() + " " + listCorrelate.get(i).getNumberOfSameMajor());
			Pearson pe = new Pearson();
			UniversityPoint uniPoint = reviewService.getPointById(listCorrelate.get(i).getUniversityId());
			University uni = uniRepo.findByUniId(listCorrelate.get(i).getUniversityId());
			double r = 0.0;
			if(uniPoint == null){
				if(listCorrelate.get(i).getNumberOfSameMajor()/10 > 1){
					r = 1 * 0.7;
				}else{
					r = listCorrelate.get(i).getNumberOfSameMajor()/n * 0.7;
				}
				pe.setR(r);
			}else{
				if(listCorrelate.get(i).getNumberOfSameMajor()/10 > 1){
					r = 1 * 0.7 + uniPoint.getRecommentPoint()/100 * 0.3;
				}else{
					r = listCorrelate.get(i).getNumberOfSameMajor()/n * 0.7 + uniPoint.getRecommentPoint()/100 * 0.3;
				}
				pe.setR(r);
			}
			pe.setUniversity(uni);
			listPearson.add(pe);
		}
		Collections.sort(listPearson, new Pearson());
		return listPearson.subList(0, listCorrelate.size()>=5 ? 5 : listCorrelate.size());
	}
	public List<University> getListUniSameMajor(int majorId){
		List<University> listUni = uniRepo.findByMajor(majorId);
		return listUni;	
	}
}
