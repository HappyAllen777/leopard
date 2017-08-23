package com.geelun.framework.base.superclass.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.MyJdbcTemplate;
import com.geelun.framework.persistence.Page;
import com.geelun.framework.persistence.SQLPreparer;
import com.geelun.framework.persistence.util.SQLUtil;
import com.geelun.framework.util.DateTimeUtil;

/**
 * 提供基础增删改查方法
 * 
 * @author Allen
 * @param <T>
 */
@Component
public abstract class BaseModelDao<T extends BaseModel> {
	@Autowired
	private MyJdbcTemplate myJdbcTemplate;

	/**
	 * <pre>
	 * 获取表to类转换器
	 * @return
	 * 
	 * <pre>
	 */
	public abstract RowMapper<T> getRowMapper();

	/**
	 * <pre>
	 * 获取该表对应的空对象
	 * @return
	 * 
	 * <pre>
	 */
	public abstract T getEmptyObject();

	/**
	 * <pre>
	 * 保存对象
	 * @param t ： 要保存的对象
	 * @return ： 保存成功返回1，否则返回0
	 * 
	 * <pre>
	 */
	public int add(T t) {
		SQLPreparer sqlPreparer = SQLUtil.genInsertSQLPreparer(t);
		return myJdbcTemplate.update(sqlPreparer);
	}

	/**
	 * <pre>
	 * 删除对象
	 * @param id
	 * @return ： 保存成功返回1，否则返回0
	 * 
	 * <pre>
	 */
	public int delete(String id) {
		String tableName = getEmptyObject().getTableName();

		SQLPreparer sqlPreparer = SQLUtil.genDeleteSQLPreparer(tableName, id);

		return myJdbcTemplate.update(sqlPreparer);
	}

	public int update(String id, List<String> properties, Object[] newValues) {
		String tableName = getEmptyObject().getTableName();
		StringBuffer setSQL = SQLUtil.genSetSQLByProperties(properties);
		String sql = "update " + tableName + " set " + setSQL + " where id=?";
		Object[] params = new Object[newValues.length + 1];
		int index = 0;
		for (Object param : newValues) {
			params[index++] = param;
		}
		params[index] = id;
		// 解决时间问题
		SimpleDateFormat datetimeFormatter = DateTimeUtil.getDateTimeFormatter();
		for (int i = 0; i < params.length; i++) {
			Object param = params[i];
			if (param instanceof Date) {
				params[i] = datetimeFormatter.format((Date) param);
			}
		}
		return myJdbcTemplate.update(sql, params);
	}

	/**
	 * <pre>
	 * 根据id获取对象
	 * @param id
	 * @return
	 * 
	 * <pre>
	 */
	public T get(String id) {
		String tableName = getEmptyObject().getTableName();
		SQLPreparer sqlPreparer = SQLUtil.genSelectSQLPreparer(id, tableName);
		return myJdbcTemplate.queryForObject(sqlPreparer, getRowMapper());
	}

	/**
	 * <pre>
	 * 更新对象
	 * @param t ： 要更新的对象
	 * @return ： 更新成功返回1
	 * 
	 * <pre>
	 */
	public int save(T t) {
		SQLPreparer sqlPreparer = SQLUtil.genUpdateSQLPreparer(t);
		return myJdbcTemplate.update(sqlPreparer);
	}

	public List<T> query(T t) {
		String tableName = getEmptyObject().getTableName();
		String sql = "select * from " + tableName;
		return myJdbcTemplate.query(sql, new Object[] {}, getRowMapper());
	}

	public Page<T> list(Page<T> page) {
		int pageNumber = page.getNumber();
		int pageSize = page.getSize();
		int start = (pageNumber - 1) * pageSize;
		String orderBy = page.getOrderBy();

		String tableName = getEmptyObject().getTableName();
		String sql = "select * from " + tableName + (orderBy == null ? "" : " order by " + orderBy) + " limit ?,?";
		String countSql = "select count(1) from " + tableName + "";

		List<T> list = myJdbcTemplate.query(sql, new Object[] { start, pageSize }, getRowMapper());
		int totalSize = myJdbcTemplate.queryForInt(countSql, new Object[] {});

		page.setTotalSize(totalSize);
		page.countVisibility();
		page.setList(list);
		return page;
	}

}
