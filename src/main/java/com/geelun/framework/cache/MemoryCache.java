package com.geelun.framework.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache implements CacheService {
	private Map<String, Object> cache = new HashMap<String, Object>();

	@Override
	public Object get(String cacheKey) {
		return cache.get(cacheKey);
	}

	@Override
	public void put(String cacheKey, Object cache) {
		this.cache.put(cacheKey, cache);
	}

}
