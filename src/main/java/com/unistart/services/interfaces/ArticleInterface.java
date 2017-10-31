package com.unistart.services.interfaces;

import java.util.Date;
import java.util.List;

import com.unistart.entities.Article;

public interface ArticleInterface {

	boolean saveArticle(String code, String title, String description, String contents, String image, Date createDate, int uniId);
	boolean deleteArticle(int id);
	List<Article> listAllArticle();

}
