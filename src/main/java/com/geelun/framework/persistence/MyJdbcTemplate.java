package com.geelun.framework.persistence;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MyJdbcTemplate extends JdbcTemplate {

	public int update(SQLPreparer sqlPreparer) {
		String sql = sqlPreparer.getSql();
		Object[] params = sqlPreparer.getParams();
		return super.update(sql, params);
	}

	public <T> T queryForObject(SQLPreparer sqlPreparer, RowMapper<T> rowMapper) throws DataAccessException {
		String sql = sqlPreparer.getSql();
		Object[] params = sqlPreparer.getParams();
		T t = null;
		try {
			t = super.queryForObject(sql, params, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("[error]:" + sqlPreparer + " cannot find a object");
		}
		return t;
	}
}
