package com.geelun.framework.base.attachment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.geelun.framework.base.attachment.model.Attachment;
import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.persistence.MySQLBaseModelRowMapper;

@Component
public class AttachmentDao extends BaseModelDao<Attachment> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Attachment emptyObject = new Attachment();

	private RowMapper<Attachment> rowMapper = new MySQLBaseModelRowMapper<Attachment>(getEmptyObject());

	@Override
	public RowMapper<Attachment> getRowMapper() {
		return rowMapper;
	}

	@Override
	public Attachment getEmptyObject() {
		return emptyObject;
	}

}
