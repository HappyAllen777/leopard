package com.geelun.framework.base.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.base.superclass.service.BaseModelService;
import com.geelun.framework.base.user.dao.UserDao;
import com.geelun.framework.base.user.model.User;

@Service
public class UserService extends BaseModelService<User> {

	@Autowired
	private UserDao userDao;

	@Override
	public BaseModelDao getDao() {
		return userDao;
	}

}
