package com.unistart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.Favorite;
import com.unistart.services.interfaces.FavoriteInterface;

@RestController
@RequestMapping(UrlConstant.FAVORITE)
public class FavoriteController {
	@Autowired
	private FavoriteInterface favoriteService;
	
	@RequestMapping(value = UrlConstant.CHECK_FAVORITE, method = RequestMethod.POST)
	public ResponseEntity<?> checkFavorite(@RequestBody Favorite favorite) {
		int userId = favorite.getUser().getId();
		int majorUniId = favorite.getMajorUni().getId();

		boolean isSuccess = favoriteService.checkFavorite(userId, majorUniId);
		return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
	}
}
