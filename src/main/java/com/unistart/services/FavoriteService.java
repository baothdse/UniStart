package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Favorite;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.Users;
import com.unistart.repositories.FavoriteRepository;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.FavoriteInterface;


@Service
@Transactional
public class FavoriteService implements FavoriteInterface{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MajorUniRepository majorUniRepo;
	@Autowired
	private FavoriteRepository favoriteRepo;
	
	private Users user;
	private MajorUniversity majorUni;
	private Favorite favorite;
	
	@Override
	public boolean saveFavorite(int userId, int majorUniId) {
		user = userRepo.findById(userId);
		majorUni = majorUniRepo.findById(majorUniId);
		
		if(user != null && majorUni != null){
			favorite = new Favorite();
			favorite.setUser(user);
			favorite.setMajorUni(majorUni);
			favoriteRepo.save(favorite);
			return true;
		}
		return false;
	}

}
