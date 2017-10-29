package com.unistart.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		int trainSystem = university.getTrainSystem().getId();
		boolean isCreated = universityService.addUniversity(code, name, email, phone, logo, image, priority, description,trainSystem);
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

	@RequestMapping(value = UrlConstant.FIND_BY_ID, method = RequestMethod.POST)
	public ResponseEntity<?> findById(@RequestBody University uni) {
		int universityId = uni.getId();
		University university = universityService.getUniversityById(universityId);
		return new ResponseEntity<University> (university, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.FIND_BY_MAJOR_ID, method = RequestMethod.GET)
	public ResponseEntity<?> findUniByMajorId(@RequestParam(value = "majorId") int majorId) {
		listUniversity = universityService.findUniversityByMajorId(majorId);
		return new ResponseEntity<List<University>> (listUniversity, HttpStatus.OK);
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
					MajorUniversity majorUni = majorService.findByMajorIdAndUniId(listMajorId[index], uni.getUniversity().getId());
					if(majorUni == null){
						Major major = majorService.getMajorById(listMajorId[index]);
				        isSave = majorService.saveMajorUniversity(major, univer); 
					}else if(majorUni != null && majorUni.getIsActive() == false){
					    majorUni.getId();
					    isSave = majorService.changeActive(majorUni.getId(), true);
					}else{
						isSave = true;
					}
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
	
	@RequestMapping(value = UrlConstant.REMOVE_MAJOR_UNI, method = RequestMethod.POST)
	public ResponseEntity<?> removeMajorOfUniversity(@RequestBody LocationMajor majorUni) {
		boolean isRemove = false;
		int listMajorId[] = null;
		listMajorId = majorUni.getMajorId();
		int uniId = majorUni.getUniversity().getId();
		for(int index = 0; index < listMajorId.length; index++){
			MajorUniversity mu = majorService.findByMajorIdAndUniId(listMajorId[index], uniId);
			if(mu != null){
				isRemove = majorService.changeActive(mu.getId(), false);
			}
		}
		if(isRemove){
			return new ResponseEntity<Boolean> (isRemove, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES009);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
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
	
	@RequestMapping(value = UrlConstant.GET_LOCATION_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<?> getLocationById(@RequestParam(value = "universityId") int universityId) {
		uni = universityService.getUniversityShort(universityId);
		if(uni != null){
			//List<University> majorUni = majorService.getUniverityWithMajor(uni);
			return new ResponseEntity<Location> (uni.getLocation(), HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES006);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = UrlConstant.UPDATE, method = RequestMethod.POST)
	public ResponseEntity<?> updateUniversity(@RequestBody University university) {
		int id = university.getId();
		String code = university.getCode();
		String name = university.getName();
		String email = university.getEmail();
		String phone = university.getPhone();
		String logo = university.getLogo();
		String image = university.getImage();
		String description = university.getDescription();
		int priority = university.getPriority();
		int trainSystem = university.getTrainSystem().getId();
		boolean isCreated = universityService.updateUniversity(id, code, name, email, phone, logo, image, priority, description,trainSystem);
		if (isCreated) {
			return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES007);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);	
	}
	
	@RequestMapping(value = UrlConstant.DELETE, method = RequestMethod.POST)
	public ResponseEntity<?> deleteUniversity(@RequestBody University university) {
		int id = university.getId();
		boolean isCreated = universityService.deleteUniversity(id);
		return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);

	}

	@RequestMapping(value = UrlConstant.GET_BY_LOCATION_AND_MAJOR, method = RequestMethod.GET)
	public ResponseEntity<?> getUniByLocaMajor(@RequestParam(value = "locationId") int locationId,
			@RequestParam(value = "majorId") int majorId){
		listUniversity = universityService.getUniByLocationMajor(locationId, majorId);
		if(listUniversity == null){
			error = new ErrorNotification(ErrorConstant.MES014);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<University>>(listUniversity, HttpStatus.OK);
	}

	@RequestMapping(value = UrlConstant.GET_BY_MAJOR_ID, method = RequestMethod.GET)
	public ResponseEntity<?> getUniByMajorId(@RequestParam(value = "majorId") int majorId) {
		listUniversity = universityService.getUniByMajorId(majorId);
		return new ResponseEntity<List<University>> (listUniversity, HttpStatus.OK);
	}
  
	@RequestMapping(value = UrlConstant.GET_UIVERSITY_BY_LOCATION, method = RequestMethod.GET)
	public ResponseEntity<?> getUniByLocationId(@RequestParam(value = "locationId") int locationId) {
		listUniversity = universityService.getUniByLocationId(locationId);
		return new ResponseEntity<List<University>> (listUniversity, HttpStatus.OK);
	}

//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public ResponseEntity<?> UploadFile(MultipartHttpServletRequest request) throws IOException {
//      Iterator<String> itr = request.getFileNames();
//      MultipartFile file = request.getFile(itr.next());
//      String fileName = file.getOriginalFilename();
//      File dir = new File("src/main/resources/img/");
//      if(!dir.exists()){
//    	  dir.mkdirs();
//      }
//      if (dir.isDirectory()) {
//        File serverFile = new File(dir, fileName);
//        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//        stream.write(file.getBytes());
//        stream.close();
//      }
//      String urlImage = dir.getAbsolutePath() + File.separator + fileName;
//      return new ResponseEntity<String> (urlImage, HttpStatus.OK);
//	}
}
