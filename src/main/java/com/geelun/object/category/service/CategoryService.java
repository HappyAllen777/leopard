package com.geelun.object.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.base.superclass.service.BaseModelService;
import com.geelun.object.category.dao.CategoryDao;
import com.geelun.object.category.model.Category;

@Service
public class CategoryService extends BaseModelService<Category> {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public BaseModelDao<Category> getDao() {
		return categoryDao;
	}

	public int add(Category category) {
		return super.add(category);
	}

}
