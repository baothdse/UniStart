package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Article;
import com.unistart.entities.Review;
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
	
	private Article article;
	@Override
	public boolean saveArticle(String code, String title, String description, String contents, String image,
			int uniId) {
		university = universityRepo.findById(uniId);
		if (university != null) {
			article = new Article();
			article.setUniversity(university);
			article.setCode(code);
			article.setTitle(title);
			article.setDescription(description);
			article.setContents(contents);
			article.setImage(image);
			article.setIsActive(true);
			articleRepo.save(article);
			return true;
		}
		return false;
	}

}
