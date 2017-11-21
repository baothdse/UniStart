package com.unistart.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unistart.constant.ErrorConstant;
import com.unistart.constant.UrlConstant;
import com.unistart.entities.Article;
import com.unistart.entities.ArticleTag;
import com.unistart.entities.Tag;
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
	public ResponseEntity<?> saveArticle(@RequestBody Article article){
		String code = article.getCode();
		String title = article.getTitle();
		String description = article.getDescription();
		String contents = article.getContents();
		String image = article.getImage();
//		Date createDate = Calendar.getInstance().getTime();
		//SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		int uniId = article.getUniversity().getId();
		boolean isSuccess = articleService.saveArticle(code, title, description, contents, image, uniId);
		boolean isSaveTag = articleService.saveTag(code, article.getTags());
		if (isSuccess && isSaveTag) {
			return new ResponseEntity<Boolean> (isSuccess, HttpStatus.OK);
		} else {
			error = new ErrorNotification(ErrorConstant.MES016);
			return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = UrlConstant.UPDATE, method = RequestMethod.POST)
	public ResponseEntity<?> updateArticle(@RequestBody Article article) {
		int id = article.getId();
		String code = article.getCode();
		String title = article.getTitle();
		String description = article.getDescription();
		String contents = article.getContents();
		String image = article.getImage();
		int uniId = article.getUniversity().getId();
		boolean isCreated = articleService.updateArticle(id, code, title, description, contents, image, uniId,article.getTags());
//		boolean isUpdateTag = articleService.updateTag(id, article.getTags());
		if (isCreated) {
			return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
		}
		error = new ErrorNotification(ErrorConstant.MES015);
		return new ResponseEntity<ErrorNotification> (error, HttpStatus.CONFLICT);	
  }
  
	@RequestMapping(value = UrlConstant.DELETE, method = RequestMethod.POST)
	public ResponseEntity<?> deleteArticle(@RequestBody Article article) {
		int id = article.getId();
		boolean isCreated = articleService.deleteArticle(id);
		return new ResponseEntity<Boolean> (isCreated, HttpStatus.OK);
}
	
	@RequestMapping(value = UrlConstant.SHOW_ARTICLE, method = RequestMethod.GET)
	public ResponseEntity<?> listAllArticle(){
		listArticle = articleService.listAllArticle();
		return new ResponseEntity<List<Article>>(listArticle, HttpStatus.OK);
	}
	

	@RequestMapping(value = UrlConstant.GET_NEWEST_ARTICLE, method = RequestMethod.GET)
	public ResponseEntity<?> getNewestArticle(@RequestParam(value = "universityId") int universityId){
		listArticle = articleService.getNewestArticle(universityId);
		return new ResponseEntity<List<Article>>(listArticle, HttpStatus.OK);
	}

	@RequestMapping(value = UrlConstant.GET_ARTICLE_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<?> getArticleById(@RequestParam(value = "articleId") int articleId){
		Article article = articleService.getArticleById(articleId);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@RequestMapping(value = UrlConstant.GET_TAG_ARTICLE, method = RequestMethod.GET)
	public ResponseEntity<?> getTagOfArticle(@RequestParam(value = "articleId") int articleId){
		List<ArticleTag> listTag = articleService.getTagOfArticle(articleId);
		return new ResponseEntity<List<ArticleTag>>(listTag, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstant.GET_YOUR_ARTICLE, method = RequestMethod.GET)
	public ResponseEntity<?> getYourArticle(@RequestParam(value = "userId") int userId){
		List<Article> listTag = articleService.getYourArticle(userId);
		return new ResponseEntity<List<Article>>(listTag, HttpStatus.OK);
	}

}
