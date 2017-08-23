package com.geelun.framework.persistence;

public class SQLPreparer {
	private String sql;

	private Object[] params;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public SQLPreparer(String sql, Object[] params) {
		setSql(sql);
		setParams(params);
	}

	@Override
	public String toString() {
		return sql;
	}

}
