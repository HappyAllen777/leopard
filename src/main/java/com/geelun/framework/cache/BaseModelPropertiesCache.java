package com.geelun.framework.cache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseModelPropertiesCache {
	private static Map<String, List<Method>> propertiesCache = new HashMap<String, List<Method>>();

	public static List<Method> getPropertiesByClassName(String className) {
		return propertiesCache.get(className);
	}

	public static void cacheProperties(String className, List<Method> properties) {
		propertiesCache.put(className, properties);
	}
}
