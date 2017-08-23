package com.geelun.object.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.base.superclass.service.BaseModelService;
import com.geelun.object.article.dao.ArticleDao;
import com.geelun.object.article.model.Article;

@Service
public class ArticleService extends BaseModelService<Article> {
	@Autowired
	private ArticleDao articleDao;

	public int add(Article article) {
		return articleDao.add(article);
	}

	public List<Article> query(Article article) {
		return articleDao.query(article);
	}

	@Override
	public BaseModelDao<Article> getDao() {
		return articleDao;
	}
}
