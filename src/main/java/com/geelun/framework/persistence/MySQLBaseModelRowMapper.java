package com.geelun.framework.persistence;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.util.ArrayUtil;
import com.geelun.framework.util.ReflectUtil;

public class MySQLBaseModelRowMapper<T extends BaseModel> implements RowMapper<T> {

	public MySQLBaseModelRowMapper(T t) {
		setT(t);
	}

	public T getT() {
		return t;
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		Class clazz = t.getClass();
		t = (T) ReflectUtil.newInstance(clazz);

		List<Field> persistentFields = ReflectUtil.getPersistentFields(t);

		if (ArrayUtil.isNotEmpty(persistentFields)) {
			for (Field field : persistentFields) {
				setColumnValuetoFieldValue(t, field, rs);
			}
		}
		return t;
	}

	private void setColumnValuetoFieldValue(BaseModel baseModel, Field field, ResultSet rs) throws SQLException {
		String fieldName = field.getName();
		Class fieldType = field.getType();
		String fieldTypeName = fieldType.getSimpleName();

		if (fieldType == Boolean.class || StringUtils.equalsIgnoreCase(fieldTypeName, "boolean")) {
			// MySQL中java的boolean映射为tinyint字段
			Integer o = rs.getInt(fieldName);
			// 等于1则认为是true
			ReflectUtil.setFieldValue(t, field, o == 1);
		} else {
			Object fieldValue = rs.getObject(fieldName);
			ReflectUtil.setFieldValue(t, field, fieldValue);
		}
	}

	public void setT(T t) {
		this.t = t;
	}

	private T t;

}
