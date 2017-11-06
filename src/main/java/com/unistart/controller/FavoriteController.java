package com.unistart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.UrlConstant;
import com.unistart.entities.Favorite;
import com.unistart.services.interfaces.FavoriteInterface;

@RestController
@RequestMapping(UrlConstant.FAVORITE)
public class FavoriteController {

	@Autowired
	private FavoriteInterface favoriteService;
	
	private List<Favorite> listFavorite;
	
	@RequestMapping(value = UrlConstant.GET_USER_FAVORITE, method = RequestMethod.GET)
	public ResponseEntity<?> getFavorite(@RequestParam(value = "userId") int userId) {
		listFavorite = favoriteService.listAllFavorite(userId);
		return new ResponseEntity<List<Favorite>>(listFavorite, HttpStatus.OK);
	}
}
