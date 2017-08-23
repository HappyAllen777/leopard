package com.geelun.framework.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistentFieldsCache {
	private static Map<String, List<Field>> fieldsCache = new HashMap<String, List<Field>>();

	public static List<Field> getFieldsByTableName(String tableName) {
		return fieldsCache.get(tableName);
	}

	public static void cachePersistentFields(String tableName, List<Field> persistentFields) {
		fieldsCache.put(tableName, persistentFields);
	}
}
