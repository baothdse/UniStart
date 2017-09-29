package com.unistart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Location;
import com.unistart.entities.Major;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.University;
import com.unistart.entities.customentities.LocationMajor;
import com.unistart.entities.customentities.SearchEntity;
import com.unistart.error.ErrorNotification;
import com.unistart.services.MajorService;
import com.unistart.services.interfaces.MajorServiceInterface;
import com.unistart.services.interfaces.UniversityServiceInterface;

@RestController
@RequestMapping(UrlConstant.UNIVERSITY)
public class UniversityController {
	
	private ErrorNotification error;
	
	@Autowired
	private UniversityServiceInterface universityService;
	
	@Autowired
	private MajorServiceInterface majorService;
	
	private List<University> listUniversity;
	private University uni;
	
	@RequestMapping(value = UrlConstant.SHOW_UNIVERSITY, method = RequestMethod.GET)
	public ResponseEntity<?> listAllUniversity(){
		listUniversity = universityService.listAllUniversityName();
		return new ResponseEntity<List<University>>(listUniversity, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.CREATE, method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<?> createUniversity(@RequestBody University university) {
		String code = university.getCode();
		String name = university.getName();
		String email = university.getEmail();
		String phone = university.getPhone();
		String logo = university.getLogo();
		String image = university.getImage();
		String description = university.getDescription();
		int priority = university.getPriority();
		boolean isCreated = universityService.addUniversity(code, name, email, phone, logo, image, priority, description);
		University uni = universityService.getUniversityByCode(code);
		if (isCreated) {
			return new ResponseEntity<University> (uni, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES003);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = UrlConstant.SEARCH, method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<?> searchUniversity(@RequestBody SearchEntity searchEntity){
		int majorId = searchEntity.getMajorId();
		int universityId = searchEntity.getUniversityId();
		int locationId = searchEntity.getLocationId();
		
		listUniversity = universityService.findUniversity(majorId, universityId, locationId);
		return new ResponseEntity<List<University>>(listUniversity, HttpStatus.OK);
	}
	@RequestMapping(value = UrlConstant.UPDATE_LOCATION_MAJOR, method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody LocationMajor uni) {
		boolean isCreated = false, isSave = false;
		int listMajorId[] = null;
		listMajorId = uni.getMajorId();
		University univer = universityService.getUniversityById(uni.getUniversity().getId());
		if(univer !=null){
			if(listMajorId != null){
				for (int index = 0; index < listMajorId.length; index++) {
					Major major = majorService.getMajorById(listMajorId[index]);
			        isSave = majorService.saveMajorUniversity(major, univer); 
				}
			}else{
				isSave= true;
			}
			if(uni.getLocation() != null){
				isCreated = universityService.addLocation(uni.getLocation().getId(), uni.getUniversity().getId());
			}else{
				isCreated = true;
			}
			if (isCreated && isSave) {
				return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
			}else{
				error = new ErrorNotification(ErrorConstant.MES005);
				return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
			}
		}else{
			error = new ErrorNotification(ErrorConstant.MES006);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = UrlConstant.GET_UNIVERSITY_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<?> getUniversityById(@RequestParam(value = "universityId") int universityId) {
		uni = universityService.getUniversityById(universityId);
		if(uni != null){
			//List<University> majorUni = majorService.getUniverityWithMajor(uni);
			return new ResponseEntity<University> (uni, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES006);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		
	}
}
