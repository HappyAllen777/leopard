package com.geelun.framework.base.user.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.base.user.model.User;

@Component
public class UserDao extends BaseModelDao<User> {

	@Override
	public RowMapper<User> getRowMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getEmptyObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
