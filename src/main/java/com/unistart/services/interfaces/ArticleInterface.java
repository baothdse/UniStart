package com.unistart.services.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.unistart.entities.Article;
import com.unistart.entities.ArticleTag;
import com.unistart.entities.Tag;

public interface ArticleInterface {

	boolean saveArticle(String code, String title, String description, String contents, String image, int uniId);
	boolean saveTag(String code, int[] tags);
	boolean updateTag(int artcleId, int[] tags);
	boolean deleteArticle(int id);
	List<Article> listAllArticle();

	boolean updateArticle(int id, String code, String title, String description, String contents, String image,
			int uniId, int[] tags);

	List<Article> getNewestArticle(int universityId);
    
	Article getArticleById(int id);
	
	List<ArticleTag> getTagOfArticle(int articleId);
	
	List<Article> getYourArticle(int userId);
}
