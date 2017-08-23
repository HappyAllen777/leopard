package com.geelun.framework.cache;

public interface CacheService {
	public Object get(String cacheKey);

	public void put(String cacheKey, Object cache);
}
