/**
 * @title RedisZoneUserClientService.java
 * @package cn.net.fone.player.web.cache.service
 * @description 
 * @author 陆林军
 * @update 2013-6-15 14:31:30
 * @version V1.0
 * 版权所有: 版权所有(C) 2013，北京风网信息技术有限公司
 */
package com.law.commons.redis.cache.service;

import java.util.Map;
import java.util.Set;






import com.law.commons.redis.cache.ICacheClient;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
 
public class RedisClientService {

	public Set<String> keys(String pattern) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.keys(pattern);

		} finally {
			ica.release();
		}
	}

	/**
	 * 删除所有
	 * 
	 * @param id
	 * @return
	 */
	public void deleteAll() {
		ICacheClient ica = new RedisClient();
		try {
			ica.deleteAll();

		} finally {
			ica.release();
		}
	}

	public void delete(String... key) {
		ICacheClient ica = new RedisClient();
		try {
			ica.del(key);

		} finally {
			ica.release();
		}
	}

	// sets
	public boolean sismember(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.sismember(key, member);

		} finally {
			ica.release();
		}
	}

	public Set<String> smembers(String key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.smembers(key);

		} finally {
			ica.release();
		}
	}

	public Set<byte[]> smembers(byte[] key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.smembers(key);

		} finally {
			ica.release();
		}
	}

	public Long sadd(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.sadd(key, member);

		} finally {
			ica.release();
		}
	}

	public Long srem(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.srem(key, member);

		} finally {
			ica.release();
		}
	}

	// hashs
	public boolean hexists(String key, String field) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hexists(key, field);

		} finally {
			ica.release();
		}
	}

	public String hget(String key, String field) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hget(key, field);

		} finally {
			ica.release();
		}
	}

	public Long hdel(String key, String field) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hdel(key, field);

		} finally {
			ica.release();
		}
	}

	public byte[] hget(byte[] key, byte[] field) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hget(key, field);

		} finally {
			ica.release();
		}
	}

	public Map<String, String> hgetAll(String key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hgetAll(key);

		} finally {
			ica.release();
		}
	}

	public Map<byte[], byte[]> hgetAll(byte[] key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hgetAll(key);

		} finally {
			ica.release();
		}
	}

	public Long hset(String key, String field, String value) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.hset(key, field, value);

		} finally {
			ica.release();
		}
	}

	// strings
	public boolean exists(String key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.exists(key);

		} finally {
			ica.release();
		}
	}

	public String get(String key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.get(key);

		} finally {
			ica.release();
		}
	}

	public byte[] get(byte[] key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.get(key);

		} finally {
			ica.release();
		}
	}

	public String set(String key, String value) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.set(key, value);

		} finally {
			ica.release();
		}
	}

	public String set(byte[] key, byte[] value) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.set(key, value);

		} finally {
			ica.release();
		}
	}

	public Long incrBy(String key, long num) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.incrBy(key, num);

		} finally {
			ica.release();
		}
	}

	public String setex(String key, int time, String value) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.setex(key, time, value);

		} finally {
			ica.release();
		}
	}

	public String setex(byte[] key, int time, byte[] value) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.setex(key, time, value);

		} finally {
			ica.release();
		}
	}

	public Set<String> sinter(String... keys) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.sinter(keys);

		} finally {
			ica.release();
		}
	}

	public Set<String> sunion(String... keys) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.sunion(keys);

		} finally {
			ica.release();
		}
	}

	/**
	 * Delete all the keys matching the glob-style pattern as space separated
	 * strings. For example if you have in the database the keys "foo" and
	 * "foobar" the command will delete "foo" and "foobar".
	 * 
	 * @param pattern
	 *            like (foo*)
	 */
	public void deletePtn4String(String pattern) {
		ICacheClient ica = new RedisClient();
		try {
			ica.deletePtn4String(pattern);

		} finally {
			ica.release();
		}
	}

	// zset
	public Long zadd(String key, Double score, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zadd(key, score, member);

		} finally {
			ica.release();
		}
	}

	public Long zrem(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrem(key, member);

		} finally {
			ica.release();
		}
	}

	public Long zrank(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrank(key, member);

		} finally {
			ica.release();
		}
	}

	public Set<String> zrange(String key, int start, int end) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrange(key, start, end);

		} finally {
			ica.release();
		}
	}

	public Set<String> zrangeByScore(String key, int min, int max) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrangeByScore(key, min, max);

		} finally {
			ica.release();
		}
	}

	public Long zcard(String key) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zcard(key);

		} finally {
			ica.release();
		}
	}

	public Set<String> zrevrange(String key, int start, int end) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrevrange(key, start, end);

		} finally {
			ica.release();
		}
	}

	public Set<String> zrevrangeByScore(String key, int max, int min) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrevrangeByScore(key, max, min);

		} finally {
			ica.release();
		}
	}

	public Long zrevrank(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zrevrank(key, member);

		} finally {
			ica.release();
		}
	}

	public Double zscore(String key, String member) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.zscore(key, member);

		} finally {
			ica.release();
		}
	}

	public void release() {

	}

	public String watch(String... keys) {
		ICacheClient ica = new RedisClient();
		try {
			return ica.watch(keys);

		} finally {
			ica.release();
		}
	}

	public Transaction multi() {
		ICacheClient ica = new RedisClient();
		try {
			return ica.multi();

		} finally {
			ica.release();
		}
	}

	public Long expire(String key, int seconds){
		ICacheClient ica = new RedisClient();
		try {
			return ica.expire(key,seconds);

		} finally {
			ica.release();
		}
	}

	public Pipeline pipelined(){
		ICacheClient ica = new RedisClient();
		try {
			return ica.pipelined();

		} finally {
			ica.release();
		}
	}

	public String rename(String oldkey, String newkey){
		ICacheClient ica = new RedisClient();
		try {
			return ica.rename(oldkey,newkey);

		} finally {
			ica.release();
		}
	}

}
