package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Article;
import com.unistart.entities.ArticleTag;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Integer>{
	
	@Query("select a from ArticleTag a where a.article.id=?1 and a.majorUni.id=?2")
    ArticleTag findByArticleIdAndMajorUniId(int articleId, int majorUniId);
	
	@Query("select a from ArticleTag a where a.article.id=?1")
    List<ArticleTag> findByArticleId(int articleId);

	
	@Query("delete from ArticleTag a where a.id=?1 ")
	void deleteTag(int id);
}
