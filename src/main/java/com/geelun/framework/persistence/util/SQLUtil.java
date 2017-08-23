package com.geelun.framework.persistence.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.cache.TableSQLCacheManager;
import com.geelun.framework.persistence.SQLPreparer;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.exception.FieldTypeException;
import com.geelun.framework.util.ArrayUtil;
import com.geelun.framework.util.DateTimeUtil;
import com.geelun.framework.util.ReflectUtil;
import com.geelun.framework.util.StringUtil;

/**
 * 提供sql拼接方法
 * 
 * @author Allen
 * 
 */
public class SQLUtil {

	/**
	 * 根据业务对象，生成insert的sql
	 * 
	 * @param baseModel
	 * @return
	 */
	public static SQLPreparer genInsertSQLPreparer(BaseModel baseModel) {
		String tableName = baseModel.getTableName();

		String cacheSQL = TableSQLCacheManager.getInsertSQLByTableName(tableName);
		List<Field> fields = ReflectUtil.getPersistentFields(baseModel);
		Object[] params = genParams(baseModel, fields);

		if (cacheSQL != null) {
			System.out.println("TableSQLCache hit: " + cacheSQL);
			return new SQLPreparer(cacheSQL, params);
		}

		int fieldSize = ArrayUtil.getRealSize(fields);
		if (fieldSize == 0) {
			// TODO : 虽然一般不会发生这种情况，但是还是得处理这种异常情况
		}
		String insertSQL = appendInsertSQL(tableName, fields);
		TableSQLCacheManager.cacheInsertSQL(tableName, insertSQL);
		return new SQLPreparer(insertSQL, params);
	}

	public static SQLPreparer genDeleteSQLPreparer(String tableName, String objectId) {
		String deleteSQL = "delete from " + tableName + " where id=?";
		Object[] params = new Object[] { objectId };

		SQLPreparer preparer = new SQLPreparer(deleteSQL, params);
		return preparer;
	}

	/**
	 * 根据业务对象，生成update where id=?的sql
	 * 
	 * @param baseModel
	 * @return
	 */
	public static SQLPreparer genUpdateSQLPreparer(BaseModel baseModel) {
		String tableName = baseModel.getTableName();

		String cacheSQL = TableSQLCacheManager.getUpdateSQLByTableName(tableName);
		List<Field> fields = ReflectUtil.getPersistentFields(baseModel);
		Object[] params = genParamsAndSetIdRail(baseModel, fields);

		if (cacheSQL != null) {
			System.out.println("TableSQLCache hit: " + cacheSQL);
		}

		int fieldSize = ArrayUtil.getRealSize(fields);
		if (fieldSize == 0) {
			// TODO :
		}
		String updateSQL = appendUpdateSQL(tableName, fields);
		TableSQLCacheManager.cacheUpdateSQL(tableName, updateSQL);
		return new SQLPreparer(updateSQL, params);
	}

	public static SQLPreparer genSelectSQLPreparer(BaseModel baseModel) {
		String id = baseModel.getId();
		String tableName = baseModel.getTableName();
		return genSelectSQLPreparer(id, tableName);
	}

	public static SQLPreparer genSelectSQLPreparer(String id, String tableName) {
		String cacheSQL = TableSQLCacheManager.getSelectSQLByTableName(tableName);

		if (cacheSQL != null) {
			System.out.println("TableSQLCache hit: " + cacheSQL);
		}

		String selectSQL = appendSelectSQL(tableName);
		TableSQLCacheManager.cacheSelectSQL(tableName, selectSQL);
		Object[] params = new Object[] { id };
		return new SQLPreparer(selectSQL, params);
	}

	private static String appendInsertSQL(String tableName, List<Field> fields) {
		int fieldSize = ArrayUtil.getRealSize(fields);
		StringBuffer insert = new StringBuffer("insert into " + tableName + "(");
		StringBuffer values = new StringBuffer(" values(");
		for (int i = 0; i < fieldSize; i++) {
			Field field = fields.get(i);
			Class fieldType = field.getType();
			String fieldName = field.getName();

			if (fieldType == String.class) {
				try {
					StringColumnDescriptor descriptor = ColumnUtil.getStringColumnDescriptor(field);
					if (descriptor != null) {
						fieldName = descriptor.name();
					}
				} catch (FieldTypeException e) {
				}

			}
			// 通过反射获取字段上的注解

			insert.append(fieldName + ",");
			values.append("?,");
		}

		// 把sql的,替换为)
		StringUtil.replaceLastChar(insert, ")");
		StringUtil.replaceLastChar(values, ")");
		String insertSQL = insert.append(values).toString();
		return insertSQL;
	}

	private static String appendUpdateSQL(String tableName, List<Field> fields) {
		int fieldSize = ArrayUtil.getRealSize(fields);
		StringBuffer updateSQL = new StringBuffer("update " + tableName + " set ");
		for (int i = 0; i < fieldSize; i++) {
			Field field = fields.get(i);
			String fieldName = field.getName();
			if (StringUtils.equals(fieldName, "id")) {
				// 不能updateid列，所以id列舍弃
				continue;
			}
			updateSQL.append(fieldName + "=?,");
		}

		// 把sql的,替换为)
		StringUtil.replaceLastChar(updateSQL, "");
		updateSQL.append(" where id=?");
		return updateSQL.toString();
	}

	private static String appendSelectSQL(String tableName) {
		return "select * from " + tableName + " where id=?";
	}

	private static Object[] genParams(BaseModel baseModel, List<Field> fields) {
		int fieldSize = ArrayUtil.getRealSize(fields);
		Object[] params = new Object[fieldSize];
		SimpleDateFormat datetimeFormatter = DateTimeUtil.getDateTimeFormatter();
		for (int i = 0; i < fieldSize; i++) {
			Field field = fields.get(i);
			// 对timestamp特殊处理
			Object param = ReflectUtil.getFieldValue(baseModel, field);
			if (param instanceof Timestamp) {
				param = datetimeFormatter.format((Timestamp) param);
			}
			params[i] = param;
		}
		return params;
	}

	private static Object[] genParamsAndSetIdRail(BaseModel baseModel, List<Field> fields) {
		int fieldSize = ArrayUtil.getRealSize(fields);
		Object[] params = new Object[fieldSize];
		boolean hasId = false;
		for (int i = 0; i < fieldSize; i++) {
			Field field = fields.get(i);
			String fieldName = field.getName();
			Object fieldValue = ReflectUtil.getFieldValue(baseModel, field);
			if (StringUtils.equals(fieldName, "id")) {
				params[fieldSize - 1] = fieldValue;
				hasId = true;
			} else {
				if (hasId) {
					params[i - 1] = fieldValue;
				} else {
					params[i] = fieldValue;
				}
			}
		}
		return params;
	}

	/**
	 * <pre>
	 * 根据属性生成set语句
	 * @param properties
	 * @return
	 * 
	 * <pre>
	 */
	public static StringBuffer genSetSQLByProperties(List<String> properties) {
		StringBuffer setSQL = new StringBuffer();
		for (String propertyName : properties) {
			setSQL.append(propertyName + "=?,");
		}
		setSQL = StringUtil.deleteLastChar(setSQL);
		return setSQL;
	}
}
