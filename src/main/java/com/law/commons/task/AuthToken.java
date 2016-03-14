package com.law.commons.task;

import org.apache.log4j.Logger;

import com.law.commons.po.AccessToken;
import com.law.commons.redis.cache.ICacheClient;
import com.law.commons.redis.cache.service.RedisClient;
import com.law.commons.util.SendRequester;

public class AuthToken {
	private static final Logger logger = Logger.getLogger(AuthToken.class);

	public void getToken() {
		ICacheClient cacheClient = null;
		try {
			cacheClient = new RedisClient();
			String token = cacheClient.get(AccessToken.KEY);
			SendRequester.getAuth(token);
		} catch (Exception e) {
			logger.error("get huanxin  token error :", e);
		} finally {
			cacheClient.release();
		}
	}

}
