package com.law.commons.redis.cache.service;

import com.law.commons.redis.cache.CacheClientBase;
import com.law.commons.redis.cache.RedisCacheReadInit;
import com.law.commons.redis.cache.RedisCacheWriteInit;



public class RedisClient extends CacheClientBase {

	public RedisClient() {
		super.readJedisFactory = RedisCacheReadInit.getSingletonInstance();
		super.writeJedisFactory = RedisCacheWriteInit.getSingletonInstance();
	}

}
