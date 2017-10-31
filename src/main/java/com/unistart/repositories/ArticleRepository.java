package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Modifying
	@Query("update Article a set a.isActive = false where a.id = ?1")
	void changeIsActive(int id);
}
