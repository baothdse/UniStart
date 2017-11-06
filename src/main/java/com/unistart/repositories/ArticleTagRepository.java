package com.unistart.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Article;
import com.unistart.entities.ArticleTag;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Integer>{
	
	@Query("select a from ArticleTag a where a.article.id=?1 and a.majorUni.id=?2")
    ArticleTag findByArticleIdAndMajorUniId(int articleId, int majorUniId);
	
	@Query("select new com.unistart.entities.ArticleTag(a.id,a.majorUni) from ArticleTag a where a.article.id=?1")
    List<ArticleTag> getTagByArticleId(int articleId);

	@Query("select a from ArticleTag a where a.article.id=?1")
    List<ArticleTag> findByArticleId(int articleId);
	
	@Query("select new com.unistart.entities.Article(a.article.id, a.article.code, a.article.title, a.article.createDate, a.article.description, a.article.image) from ArticleTag a where a.majorUni.id=?1")
    List<Article> findByMajorUniId(int majorUniId);
	@Transactional
	@Modifying
	@Query("delete from ArticleTag a where a.id=?1 ")
	void deleteTag(int id);
}
