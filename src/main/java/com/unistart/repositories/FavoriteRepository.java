package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{

	@Query("select f from Favorite f where f.user.id=?1")
	List<Favorite> findByUserId(int userId);
	
	@Query("select f.majorUni.major, f.majorUni.university from Favorite f where f.user.id = ?1")
	List<Favorite> findMajorUniByUserId(int userId);
}
