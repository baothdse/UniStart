package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Mbtiresult;
import com.unistart.entities.customentities.MBTIResultType;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.MBTIResultServiceInterface;

@RestController
@RequestMapping(value = UrlConstant.MBTI)
public class MBTIResultController {
	@Autowired
	private MBTIResultServiceInterface mbtiService;
	private ErrorNotification error;

	@RequestMapping(value = UrlConstant.SAVE_MBTI_RESULT, method = RequestMethod.POST)
	public ResponseEntity<?> saveMbtiResult(@RequestBody MBTIResultType mbtiResultType) {
		String mbtiName = mbtiResultType.getMbtiType().getMbtitypeName();
		int userId = mbtiResultType.getUser().getId();
		
		boolean isSuccess = mbtiService.saveMbtiResult(mbtiName, userId);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES005);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
}
