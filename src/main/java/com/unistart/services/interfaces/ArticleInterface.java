package com.unistart.services.interfaces;

import java.util.Date;

public interface ArticleInterface {

	boolean saveArticle(String code, String title, String description, String contents, String image, Date createDate, int uniId);

	boolean updateArticle(int id, String code, String title, String description, String contents, String image,
			int uniId);

}
