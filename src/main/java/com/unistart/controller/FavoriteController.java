package com.unistart.controller;


import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Favorite;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.FavoriteInterface;

@RestController
@RequestMapping(UrlConstant.FAVORITE)
public class FavoriteController {

	@Autowired
	private FavoriteInterface favoriteService;

	private List<Favorite> listFavorite;
	
	private ErrorNotification error;
	
	@RequestMapping(value = UrlConstant.SAVE_FAVORITE, method = RequestMethod.POST)
	public ResponseEntity<?> saveFavorite(@RequestBody Favorite favorite) {
		int userId = favorite.getUser().getId();
		int majorUniId = favorite.getMajorUni().getId();

		Favorite isSuccess = favoriteService.saveFavorite(userId, majorUniId);
		if (isSuccess != null) {
			return new ResponseEntity<Integer> (isSuccess.getId(), HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES005);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
  
  @RequestMapping(value = UrlConstant.CHECK_FAVORITE, method = RequestMethod.POST)
	public ResponseEntity<?> checkFavorite(@RequestBody Favorite favorite) {
		int userId = favorite.getUser().getId();
		int majorUniId = favorite.getMajorUni().getId();
		int favo = favoriteService.checkFavorite(userId, majorUniId);
		return new ResponseEntity<Integer> (favo, HttpStatus.OK);
  }

  	@RequestMapping(value = UrlConstant.GET_USER_FAVORITE, method = RequestMethod.GET)
	public ResponseEntity<?> getFavorite(@RequestParam(value = "userId") int userId) {
		listFavorite = favoriteService.listAllFavorite(userId);
		return new ResponseEntity<List<Favorite>>(listFavorite, HttpStatus.OK);
	}

	@RequestMapping(value = UrlConstant.DELETE, method = RequestMethod.POST)
	public ResponseEntity<?> deleteFavorite(@RequestBody Favorite favorite) {
		int id = favorite.getId();
		boolean isCreated = favoriteService.deleteFavorite(id);
		return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
	}
}
