package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Favorite;

public interface FavoriteInterface {

	boolean deleteFavorite(int id);
	List<Favorite> listAllFavorite(int userId);
	int checkFavorite(int userId, int majorUniId);
	Favorite saveFavorite(int userId, int majorUniId);

}
