package com.geelun.framework.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SQL缓存管理类，存放SQL内存缓存
 * 
 * @author Allen
 * 
 */
@Component
public class TableSQLCacheManager {

	@Autowired
	private static CacheService cacheService;

	private static Map<String, String> insertSQLCache = new HashMap<String, String>();
	private static Map<String, String> updateSQLCache = new HashMap<String, String>();
	private static Map<String, String> selectSQLCache = new HashMap<String, String>();

	public static String getInsertSQLByTableName(String tableName) {
		return (String)cacheService.get(tableName);
	}

	public static void cacheInsertSQL(String tableName, String insertSQL) {
		cacheService.put(tableName, insertSQL);
	}

	public static String getUpdateSQLByTableName(String tableName) {
		return updateSQLCache.get(tableName);
	}

	public static void cacheUpdateSQL(String tableName, String updateSQL) {
		updateSQLCache.put(tableName, updateSQL);
	}

	public static String getSelectSQLByTableName(String tableName) {
		return selectSQLCache.get(tableName);
	}

	public static void cacheSelectSQL(String tableName, String selectSQL) {
		selectSQLCache.put(tableName, selectSQL);
	}

}
