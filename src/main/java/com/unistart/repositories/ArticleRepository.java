package com.unistart.repositories;

import java.util.List;

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

	@Query("select new com.unistart.entities.Article(a.id, a.code, a.title) "
			+ "from Article a where a.isActive = true")
	List<Article> getListArticle();

}
