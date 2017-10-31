package com.unistart.services.interfaces;

public interface ArticleInterface {

	boolean saveArticle(String code, String title, String description, String contents, String image, int uniId);

}
