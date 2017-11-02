package com.unistart.services.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.unistart.entities.Article;

public interface ArticleInterface {

	boolean saveArticle(String code, String title, String description, String contents, String image, int uniId);
	boolean deleteArticle(int id);
	List<Article> listAllArticle();

	boolean updateArticle(int id, String code, String title, String description, String contents, String image,
			int uniId);

	List<Article> getNewestArticle(int universityId);
    
	Article getArticleById(int id);
}
