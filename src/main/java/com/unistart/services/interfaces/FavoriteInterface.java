package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Favorite;

public interface FavoriteInterface {

	List<Favorite> listAllFavorite(int userId);
	boolean checkFavorite(int userId, int majorUniId);
	boolean saveFavorite(int userId, int majorUniId);

}
