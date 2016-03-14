package com.law.commons.redis.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis工厂类
 * 
 * @author caoyong1
 * @since 2014-0-10-14
 */
public class JedisFactory {

	private JedisPoolConfig jedisPoolConfig;

	private JedisPool jedisPool;
	private String host;
	private Integer port;

	public JedisFactory(JedisPoolConfig jedisPoolConfig, String host,
			Integer port) {
		this.jedisPoolConfig = jedisPoolConfig;
		this.host = host;
		this.port = port;
		jedisPool = new JedisPool(jedisPoolConfig, host, port, 10000);
	}

	public Jedis getJedisInstance() {
		Jedis resource = null;
		while (resource == null) {
			resource = getJedisPool().getResource();
		}
		return resource;
	}

	public JedisPool getJedisPool() {
		if (jedisPool == null) {
			jedisPool = new JedisPool(jedisPoolConfig, host, port, 10000);
		}
		return jedisPool;
	}

	/**
	 * 配合使用getJedisInstance方法后将jedis对象释放回连接池中
	 * 
	 * @param jedis
	 *            使用完毕的Jedis对象
	 * @return true 释放成功；否则返回false
	 */
	public boolean release(Jedis jedis) {
		if (jedisPool != null && jedis != null) {
			jedisPool.returnResource(jedis);
			return true;
		}
		return false;
	}

	/**
	 * 释放连接池
	 */
	public void destroy() {
		jedisPool.destroy();
	}

	/**
	 * 重置链接池
	 */
	public void reset() {
		jedisPool.destroy();
		jedisPool = new JedisPool(jedisPoolConfig, host, port, 0);
	}
}