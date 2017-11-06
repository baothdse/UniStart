package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Favorite;
import com.unistart.repositories.FavoriteRepository;
import com.unistart.services.interfaces.FavoriteInterface;


@Service
@Transactional
public class FavoriteService implements FavoriteInterface{

	@Autowired
	private FavoriteRepository favoriteRepo;
	
	private Favorite favorite;
	
	@Override
	public boolean checkFavorite(int userId, int majorUniId) {
		favorite = favoriteRepo.findByUserAndMajorUniId(userId, majorUniId);
		if(favorite != null){
			return true;
		}
		return false;
	}
}
