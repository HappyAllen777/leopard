package com.geelun.object.article.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.persistence.MySQLBaseModelRowMapper;
import com.geelun.object.article.model.Article;

@Component
public class ArticleDao extends BaseModelDao<Article> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Article emptyObject = new Article();

	private RowMapper<Article> rowMapper = new MySQLBaseModelRowMapper<Article>(getEmptyObject());

	@Override
	public RowMapper<Article> getRowMapper() {
		return rowMapper;
	}

	@Override
	public Article getEmptyObject() {
		return emptyObject;
	}

}
