package com.unistart.services;

import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Article;
import com.unistart.entities.ArticleTag;
import com.unistart.entities.Favorite;
import com.unistart.entities.MajorUniversity;
import com.unistart.entities.Tag;
import com.unistart.entities.University;
import com.unistart.repositories.ArticleRepository;
import com.unistart.repositories.ArticleTagRepository;
import com.unistart.repositories.FavoriteRepository;
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
	@Autowired
	private FavoriteRepository favoriteRepo;
	
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
			int uniId, int[] tags) {
		article = articleRepo.findCodeById(id, code);
		if(article == null){
			article = articleRepo.findById(id);
			if(article != null){
				articleRepo.updateArticle(id, code, title, description, contents, image, uniId);
				updateTag(id,tags);
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
			List<ArticleTag> listTag = articleTagRepo.findByArticleId(artcleId);
			ArticleTag aT = new ArticleTag();
			ArrayList<Integer> test = new ArrayList<Integer>();
			for(int x=0;x<tags.length;x++){
				test.add(tags[x]);
			}
			for(int a=0; a<listTag.size();a++){
			    if(test.contains(listTag.get(a).getMajorUni().getId())==false){
			    	aT = articleTagRepo.findByArticleIdAndMajorUniId(artcleId, listTag.get(a).getMajorUni().getId());
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
	@Override
	public List<ArticleTag> getTagOfArticle(int articleId) {
		List<ArticleTag> listTag = articleTagRepo.getTagByArticleId(articleId);
		return listTag;
	}
	@Override
	public List<Article> getYourArticle(int userId) {
		List<Favorite> listFavorite = favoriteRepo.findByUserId(userId);
		List<Article> yourArticle = new ArrayList<>();
		List<Article> listYourArticle = new ArrayList<>();
		for(int i =0;i<listFavorite.size();i++){
			yourArticle = articleTagRepo.findByMajorUniId(listFavorite.get(i).getMajorUni().getId());
			for(int j =0;j<yourArticle.size();j++){
				listYourArticle.add(yourArticle.get(j));
			}
		}
		return removeDuplicates(listYourArticle);
	}

	public List<Article> removeDuplicates(List<Article> list) {
		 int s=0;
		List<Article> unique = new ArrayList<Article>();
		for (Article us1 : list) {
			s=0;
			for (Article us2 : unique) {
				System.out.println("id2 " + us2.getId());
				if (us1.getId() == us2.getId()) {
				   s = 1;
				   break;
				}
			}
			if (s == 0) {
				unique.add(us1);
			}
		}
		return unique;
	}

}
