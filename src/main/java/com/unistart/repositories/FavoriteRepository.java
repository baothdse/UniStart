package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{

	@Query("select f from Favorite f where f.user.id=?1")
	List<Favorite> findByUserId(int userId);
	
	Favorite findById(int id);
	
	@Modifying
	@Query("delete from Favorite f where f.id = ?1 ")
	void deleteFavorite(int id);

	@Query("select new com.unistart.entities.Favorite(f.id,f.majorUni) from Favorite f where f.user.id = ?1")
	List<Favorite> findMajorUniByUserId(int userId);

	@Query("select f from Favorite f where f.user.id = ?1 and f.majorUni.id = ?2")
	public Favorite findByUserAndMajorUniId(int userId, int majorUniId);

}
