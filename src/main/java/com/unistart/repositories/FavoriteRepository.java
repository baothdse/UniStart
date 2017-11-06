package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{

}
