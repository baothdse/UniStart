package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.Favorite;

public interface FavoriteInterface {

	List<Favorite> listAllFavorite(int userId);

}
