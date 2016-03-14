package com.law.commons.redis.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;

/**
 * 版权所有: 版权所有(C) 2014，联想信息技术有限公司<br/>
 * 内容摘要: 缓存客户端接口<br/>
 * 创建日期: 2014-10-14<br/>
 * 作　　者: caoyong1<br/>
 * 修改记录：
 */
public interface ICacheClient {

	public Set<String> keys(String pattern);

	/**
	 * 删除所有
	 * 
	 * @param id
	 * @return
	 */
	public void deleteAll();

	public void del(String... key);

	public void del(byte[]... key);

	public Long ttl(String key);
	public boolean sismember(byte[]key, byte[] member);
	// sets
	public boolean sismember(String key, String member);

	public Set<String> smembers(String key);

	public Set<byte[]> smembers(byte[] key);

	public Long sadd(byte[] key, byte[] value);

	public Long sadd(String key, String member);

	public Long srem(String key, String member);

	public Long move(String srckey, String dstkey, String member);

	// hashs
	public boolean hexists(String key, String field);

	public String hget(String key, String field);

	public Long hdel(String key, String field);

	public byte[] hget(byte[] key, byte[] field);

	public Map<String, String> hgetAll(String key);

	public Map<byte[], byte[]> hgetAll(byte[] key);

	public Long hset(String key, String field, String value);

	// strings
	public boolean exists(String key);

	public boolean exists(byte[] key);

	public String get(String key);

	public byte[] get(byte[] key);

	public String set(String key, String value);

	public String set(byte[] key, byte[] value);

	public Long incrBy(String key, long num);

	public String setex(String key, int time, String value);

	public String setex(byte[] key, int time, byte[] value);

	public Set<String> sinter(String... keys);

	public Set<String> sunion(String... keys);
//	public Set<String> substr(String key,int start,int end) ;
	/**
	 * Delete all the keys matching the glob-style pattern as space separated
	 * strings. For example if you have in the database the keys "foo" and
	 * "foobar" the command will delete "foo" and "foobar".
	 * 
	 * @param pattern
	 *            like (foo*)
	 */
	public void deletePtn4String(String pattern);

	// zset
	public Long zadd(String key, Double score, String member);

	public Long zadd(byte[] key, Double score, byte[] member);

	public Long zrem(String key, String member);

	public Long zrank(String key, String member);

	public Set<String> zrange(String key, int start, int end);

	public Set<byte[]> zrange(byte[] key, int start, int end);

	public Set<String> zrangeByScore(String key, double min, double max);

	public Long zcard(String key);

	public Long zcard(byte[] key);

	public Set<String> zrevrange(String key, int start, int end);

	public Set<byte[]> zrevrange(byte[] key, int start, int end);

	public Set<String> zrevrangeByScore(String key, double max, double min);

	public Long zrevrank(String key, String member);

	public Double zscore(String key, String member);

	public Double zscore(byte[] key, byte[] member);

	public void release();

	public String watch(String... keys);

	public Transaction multi();

	public Long expire(String key, int seconds);

	public Long expire(byte[] key, int seconds);

	public Pipeline pipelined();

	public String rename(String oldkey, String newkey);

	public Long zremrangeByScore(String key, double start, double end);// 删除排序

	// list
	public String rpop(String listKey);

	public String lindex(String listKey, Long index);

	public Long llen(String key);

	public Long lpush(String key, String value);

	public Long lrem(String key, int count, String value);

	public List<String> lrange(String key, Long start, Long end);

	public void mset(String... keyvalues);

	public String rpoplpush(final String srckey, final String dstkey);

	public Long sort(final String key, final SortingParams sortingParameters, final String dstkey);

}
