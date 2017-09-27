package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Location;
import com.unistart.entities.University;
import com.unistart.entities.customentities.SearchEntity;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.UniversityServiceInterface;

@RestController
@RequestMapping(UrlConstant.UNIVERSITY)
public class UniversityController {
	
	private ErrorNotification error;
	
	@Autowired
	private UniversityServiceInterface universityService;
	
	private List<University> listUniversity;
	
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
		boolean isCreated = universityService.addUniversity(code, name, email, phone, logo, image, description);
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
	
	@RequestMapping(value = UrlConstant.UPDATE, method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody University uni) {
		int id = uni.getLocation().getId();
		System.out.println(id);
		boolean isCreated = universityService.addLocation(uni.getLocation().getId(), uni.getId());
		if (isCreated) {
			return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES004);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
	}
}
