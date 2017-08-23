package com.geelun.object.category.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.persistence.MySQLBaseModelRowMapper;
import com.geelun.object.category.model.Category;

@Repository
public class CategoryDao extends BaseModelDao<Category> {

	private Category emptyObject = new Category();
	private RowMapper<Category> rowMapper = new MySQLBaseModelRowMapper<Category>(getEmptyObject());

	@Override
	public RowMapper<Category> getRowMapper() {
		return rowMapper;
	}

	@Override
	public Category getEmptyObject() {
		return emptyObject;
	}

}
