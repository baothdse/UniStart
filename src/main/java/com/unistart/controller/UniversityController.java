package com.unistart.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.University;
import com.unistart.services.interfaces.UniversityServiceInterface;

@RestController
@RequestMapping("/show-university")
public class UniversityController {

	@Autowired
	private UniversityServiceInterface universityService;
	private List<University> listUniversity;
	
	@RequestMapping(value = UrlConstant.SHOWUNIVERSITY, method = RequestMethod.GET)
	public ResponseEntity<?> showUniversity() {
		listUniversity = (List<University>) universityService.showUniversity();
		
		return new ResponseEntity<List<University>> (listUniversity, HttpStatus.OK); 
		
	}
}
