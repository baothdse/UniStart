package com.unistart.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Article;
import com.unistart.error.ErrorNotification;
import com.unistart.services.interfaces.ArticleInterface;

@RestController
@RequestMapping(UrlConstant.ARTICLE)
public class ArticleController {
	@Autowired
	private ArticleInterface articleService;
	
	private List<Article> listArticle;
	private ErrorNotification error;
	
	@RequestMapping(value = UrlConstant.SAVE_ARTICLE, method = RequestMethod.POST)
	public ResponseEntity<?> saveArticle(@RequestBody Article article) {
		String code = article.getCode();
		String title = article.getTitle();
		String description = article.getDescription();
		String contents = article.getContents();
		String image = article.getImage();
		Date createDate = new Date();
		System.out.println(createDate);
		//SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		int uniId = article.getUniversity().getId();
		boolean isSuccess = articleService.saveArticle(code, title, description, contents, image, createDate, uniId);
		if (isSuccess) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES005);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = UrlConstant.SHOW_ARTICLE, method = RequestMethod.GET)
	public ResponseEntity<?> listAllArticle(){
		listArticle = articleService.listAllArticle();
		return new ResponseEntity<List<Article>>(listArticle, HttpStatus.OK);
	}
}
