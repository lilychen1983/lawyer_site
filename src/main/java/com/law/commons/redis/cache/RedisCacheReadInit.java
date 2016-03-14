package com.law.commons.redis.cache;


import com.law.commons.util.PpsConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * 版权所有: 版权所有(C) 2014，联想信息技术有限公司<br/>
 * 内容摘要: 缓存客户端接口<br/>
 * 创建日期: 2014-10-14<br/>
 * 作　　者: caoyong1<br/>
 * 修改记录：
 */
public class RedisCacheReadInit {
//	private static final Integer MAXACTIVE = 500;
	private static final Integer MAXIDLE = 100;
//	private static final Integer MAXWAIT = 1000;
	private static JedisFactory instance;

	/**
	 * 获取缓存的单例
	 * 
	 * @return
	 */
	public synchronized static JedisFactory getSingletonInstance() {
		if (instance == null) {
			init();
		}
		return instance;
	}

	/**
	 * 初始化方法
	 */
	private static void init() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// jedisPoolConfig.setMaxActive(MAXACTIVE);
		// jedisPoolConfig.setMaxWait(MAXWAIT);
		jedisPoolConfig.setMaxIdle(MAXIDLE);
		jedisPoolConfig.setTestOnBorrow(false);
		jedisPoolConfig.setTestWhileIdle(false);
		// jedisPoolConfig.minEvictableIdleTimeMillis = 60000;
		// jedisPoolConfig.timeBetweenEvictionRunsMillis = 30000;
		// jedisPoolConfig.numTestsPerEvictionRun = -1;
		jedisPoolConfig.setMinEvictableIdleTimeMillis(30000);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
		jedisPoolConfig.setMaxTotal(600);
		String host = PpsConfig.getString("redis.data.read.server.host");
		Integer port = Protocol.DEFAULT_PORT;
		if (PpsConfig.getString("redis.data.read.server.port") != null)
			port = Integer.valueOf(PpsConfig.getString("redis.data.read.server.port"));
		instance = new JedisFactory(jedisPoolConfig, host, port);
	}

}
