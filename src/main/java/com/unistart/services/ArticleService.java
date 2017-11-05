package com.unistart.services;

import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Article;
import com.unistart.entities.ArticleTag;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.Tag;
import com.unistart.entities.University;
import com.unistart.repositories.ArticleRepository;
import com.unistart.repositories.ArticleTagRepository;
import com.unistart.repositories.MajorUniRepository;
import com.unistart.repositories.TagRepository;
import com.unistart.repositories.UniversityRepository;
import com.unistart.services.interfaces.ArticleInterface;

@Service
@Transactional
public class ArticleService implements ArticleInterface{

	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private ArticleRepository articleRepo;
	@Autowired
	private ArticleTagRepository articleTagRepo;
	@Autowired
	private MajorUniRepository majorUniRepo;
	
	private University university;
	private List<Article> listArticle;
	private Article article;
	@Override
	public boolean saveArticle(String code, String title, String description, String contents, String image,int uniId) {
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
//		List<Article> topArticle = new ArrayList<>();
//		if(listArticle != null){
//			for(int i=0; i<5; i++){
//				topArticle.add(listArticle.get(i));
//			}
//		}
		return listArticle.subList(0, listArticle.size()>=4 ? 4 : listArticle.size());
	}

	@Override
	public Article getArticleById(int id) {
		Article article = articleRepo.findByArtcleId(id);
		List<ArticleTag> listTag = articleTagRepo.findByArticleId(id);
		int[] tag = new int[listTag.size()];
		for(int i =0; i<listTag.size();i++){
			tag[i] = listTag.get(i).getMajorUni().getId();
		}
		article.setTags(tag);
		return article;
	}
	@Override
	public boolean saveTag(String code, int[] tags) {
		article = articleRepo.findByCode(code);
		if(article != null){
			for(int i =0; i<tags.length;i++){
				ArticleTag aT = new ArticleTag();
				aT.setArticle(article);
				MajorUniversity majorUni = majorUniRepo.findById(tags[i]);
				aT.setMajorUni(majorUni);
				articleTagRepo.save(aT);
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean updateTag(int artcleId, int[] tags) {
		article = articleRepo.findById(artcleId);
		if(article != null){
			List<ArticleTag> listTag = articleTagRepo.findByArticleId(artcleId);
			int[] majorUniId = new int[listTag.size()];
			for(int j=0; j<listTag.size();j++){
				majorUniId[j] = listTag.get(j).getMajorUni().getId();
			}
			ArticleTag aT = new ArticleTag();
			List check = Arrays.asList(tags);
			for(int a=0; a<majorUniId.length;a++){
			    if(check.contains(majorUniId[a])==false){
			    	System.out.println("id: " + majorUniId[a]);
			    	aT = articleTagRepo.findByArticleIdAndMajorUniId(artcleId, majorUniId[a]);
			    	articleTagRepo.deleteTag(aT.getId());
			    }
			}
			for(int i=0; i<tags.length;i++){
				aT = articleTagRepo.findByArticleIdAndMajorUniId(artcleId, tags[i]);
				if(aT == null){
					aT = new ArticleTag();
					aT.setArticle(article);
					MajorUniversity majorUni = majorUniRepo.findById(tags[i]);
					aT.setMajorUni(majorUni);
					articleTagRepo.save(aT);
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public List<ArticleTag> getTagOfArticle(int articleId) {
		List<ArticleTag> listTag = articleTagRepo.findByArticleId(articleId);
		return listTag;
	}
}
