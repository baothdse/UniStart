package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	Article findById(int id);
	Article findByCode(String code);
	
	@Modifying
	@Query("update Article a set a.code = ?2, a.title= ?3, a.description= ?4, a.contents = ?5,"
			+ "a.image = ?6, a.university.id = ?7 where a.id = ?1 ")
	void updateArticle(int id, String code, String title, String description, String contents, String image,
			int uniId);
  
	@Modifying
	@Query("update Article a set a.isActive = false where a.id = ?1")
	void changeIsActive(int id);

	@Query("select new com.unistart.entities.Article(a.id, a.code, a.title, a.createDate, a.description) "
			+ "from Article a where a.isActive = true order by a.createDate desc")
	List<Article> getListArticle();

}
