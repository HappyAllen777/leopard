package com.geelun.framework.base.superclass.service;

import java.util.List;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.Page;

public abstract class BaseModelService<T extends BaseModel> {

	public abstract BaseModelDao<T> getDao();

	public int add(T t) {
		return getDao().add(t);
	}

	public int delete(String id) {
		return getDao().delete(id);
	}

	public int update(String id, List<String> properties, Object[] newValues) {
		return getDao().update(id, properties, newValues);
	}

	public T get(String id) {
		return getDao().get(id);
	}

	public Page<T> list(Page<T> page) {
		return getDao().list(page);
	}
}
