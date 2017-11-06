package com.unistart.services;

import java.util.List;

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
	
	@Override
	public List<Favorite> listAllFavorite(int userId) {
		return favoriteRepo.findMajorUniByUserId(userId);
	}
}
