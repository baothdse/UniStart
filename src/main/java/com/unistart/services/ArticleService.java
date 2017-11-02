package com.unistart.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Article;
import com.unistart.entities.University;
import com.unistart.repositories.ArticleRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.ArticleInterface;

@Service
@Transactional
public class ArticleService implements ArticleInterface{

	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private ArticleRepository articleRepo;
	
	private University university;
	private List<Article> listArticle;
	private Article article;
	@Override
	public boolean saveArticle(String code, String title, String description, String contents, String image,
			int uniId) {
		article = articleRepo.findByCode(code);
		university = universityRepo.findById(uniId);
		Calendar cal = Calendar.getInstance();
		if (university != null && article == null) {
			article = new Article();
			article.setUniversity(university);
			article.setCode(code);
			article.setTitle(title);
			article.setDescription(description);
			article.setContents(contents);
			article.setImage(image);
			article.setCreateDate(cal.getTime());
			article.setIsActive(true);
			articleRepo.save(article);
			return true;
		}
		return false;
	}
	@Override
	public boolean updateArticle(int id, String code, String title, String description, String contents, String image,
			int uniId) {
		article = articleRepo.findCodeById(id, code);
		if(article == null){
			article = articleRepo.findById(id);
			if(article != null){
				articleRepo.updateArticle(id, code, title, description, contents, image, uniId);
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean deleteArticle(int id) {
		articleRepo.changeIsActive(id);
		return true;
  }
	@Override
	public List<Article> listAllArticle() {
		listArticle = articleRepo.getListArticle();
		return listArticle;
	}

	
	@Override
	public List<Article> getNewestArticle(int universityId) {
		listArticle = articleRepo.getNewestArticle(universityId);
		List<Article> topArticle = new ArrayList<>();
		if(listArticle != null){
			for(int i=0; i<5; i++){
				topArticle.add(listArticle.get(i));
			}
		}
		return topArticle;
/*		long currDate = System.currentTimeMillis();
		List<Date> date = new ArrayList<Date>();
		if (listArticle != null) {
			for(int i=0 ; i < listArticle.size(); i++) {
				date.add(listArticle.get(i).getCreateDate());
				for(int a=0; a < date.size(); a++) {
				}
				long diff = Math.abs(date.getTime() - currDate);
			}
			
		}*/
	}

	@Override
	public Article getArticleById(int id) {
		Article article = articleRepo.findById(id);
		return article;
	}
}
