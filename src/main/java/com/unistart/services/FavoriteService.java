package com.unistart.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Favorite;
import com.unistart.repositories.FavoriteRepository;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.Users;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.UserRepository;
import com.unistart.services.interfaces.FavoriteInterface;


@Service
@Transactional
public class FavoriteService implements FavoriteInterface{

	@Autowired
	private FavoriteRepository favoriteRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MajorUniRepository majorUniRepo;
	private Users user;
	private MajorUniversity majorUni;
	private Favorite favorite;
	
	@Override
	public Favorite saveFavorite(int userId, int majorUniId) {
		user = userRepo.findById(userId);
		majorUni = majorUniRepo.findById(majorUniId);
		
		if(user != null && majorUni != null){
			favorite = new Favorite();
			favorite.setUser(user);
			favorite.setMajorUni(majorUni);
			favoriteRepo.save(favorite);
			return favoriteRepo.save(favorite);
		}
		return null;
	}
  
  @Override
	public int checkFavorite(int userId, int majorUniId) {
		favorite = favoriteRepo.findByUserAndMajorUniId(userId, majorUniId);
		if (favorite != null) {
			return favorite.getId();
		}
		return -1;
  }

  	@Override
	public List<Favorite> listAllFavorite(int userId) {
		return favoriteRepo.findMajorUniByUserId(userId);
	}
 	@Override
	public boolean deleteFavorite(int id) {
		
		favorite = favoriteRepo.findById(id);
		if(favorite != null) {
			favoriteRepo.deleteFavorite(id);
			return true;
		}
		return false;
  }
}
