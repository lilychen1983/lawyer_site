package com.law.commons.redis.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class CacheClientBase implements ICacheClient {
	protected Jedis readJedis;
	protected Jedis writeJedis;
	protected JedisFactory readJedisFactory;
	protected JedisFactory writeJedisFactory;

	public CacheClientBase(){
	}

	private void checkRead() {
		if (readJedis == null)
			readJedis = readJedisFactory.getJedisInstance();
	}

	private void checkWrite() {
		if (writeJedis == null)
			writeJedis = writeJedisFactory.getJedisInstance();
	}

	@Override
	public void release() {
		if (readJedis != null)
			readJedisFactory.release(readJedis);
		if (writeJedis != null)
			writeJedisFactory.release(writeJedis);
	}

	@Override
	public void deleteAll() {
		checkWrite();
		try {
			writeJedis.flushAll();
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		 
	}

	@Override
	public boolean hexists(String key, String field) {
		checkRead();
		try {
			return readJedis.hexists(key, field);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return false;
	}

	@Override
	public String hget(String key, String field) {
		checkRead();
		
		try {
			return readJedis.hget(key, field);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public boolean sismember(String key, String member) {
		checkRead();
		
		try {
			return readJedis.sismember(key, member);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return false;
	}

	@Override
	public Set<String> smembers(String key) {
		checkRead();
		
		try {
			return readJedis.smembers(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<byte[]> smembers(byte[] key) {

		checkRead();
		
		try {
			return readJedis.smembers(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		checkRead();
		
		
		try {
			return readJedis.hgetAll(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		checkRead();
		
		try {
			return readJedis.hgetAll(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public boolean exists(String key) {
		checkRead();
		
		try {
			return readJedis.exists(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return false;
	}
	
	@Override
	public boolean exists(byte[] key) {
		checkRead();
		
		try {
			return readJedis.exists(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return false;
	}

	@Override
	public String get(String key) {
		checkRead();
		
		try {
			return readJedis.get(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public byte[] get(byte[] key) {
		checkRead();
		
		try {
			return readJedis.get(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) {
		checkRead();
		
		try {
			return readJedis.hget(key, field);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long incrBy(String key, long num) {
		checkWrite();
		
		try {
			return writeJedis.incrBy(key, num);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String set(String key, String value) {
		checkWrite();
		
		try {
			return writeJedis.set(key, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String set(byte[] key, byte[] value) {
		checkWrite();
		
		try {
			return writeJedis.set(key, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String setex(String key, int time, String value) {
		checkWrite();
		
		try {
			return writeJedis.setex(key, time, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String setex(byte[] key, int time, byte[] value) {
		checkWrite();
		
		try {
			return writeJedis.setex(key, time, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public void del(String... key) {
		checkWrite();
		
		try {
			writeJedis.del(key);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
	}

	@Override
	public Set<String> keys(String pattern) {

		checkRead();
		
		try {
			return readJedis.keys(pattern);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long sadd(String key, String member) {
		checkWrite();
		
		try {
			return writeJedis.sadd(key, member);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<String> sinter(String... keys) {
		checkRead();
		
		try {
			return readJedis.sinter(keys);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long srem(String key, String member) {
		checkWrite();
		
		try {
			return writeJedis.srem(key, member);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long hset(String key, String field, String value) {
		checkWrite();
		
		try {
			return writeJedis.hset(key, field, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String watch(String... keys) {
		checkRead();
		
		try {
			return readJedis.watch(keys);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
		
	}

	@Override
	public boolean sismember(byte[]key, byte[] member) {

		checkRead();
		try {
			return readJedis.sismember(key, member);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return false;
		
	
	
	
	}
	@Override
	public Transaction multi() {
		checkWrite();
		 
		try {
			return writeJedis.multi();
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long expire(String key, int seconds) {
		checkWrite();
		
		try {
			return writeJedis.expire(key, seconds);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}
	
	@Override
	public Long expire(byte[] key, int seconds) {
		checkWrite();
		
		try {
			return writeJedis.expire(key, seconds);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long zadd(String key, Double score, String member) {
		checkWrite();
		
		try {
			return writeJedis.zadd(key, score, member);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}
	
	

	@Override
	public Long zadd(byte[]  key, Double score,byte[] value) {
		checkWrite();
		
		try {
			return writeJedis.zadd(key, score, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}
	

	@Override
	public Long sadd(byte[]  key,  byte[] value) {
		checkWrite();
		
		try {
			return writeJedis.sadd(key, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long zcard(String key) {
		checkRead();
		
		try {
			return readJedis.zcard(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}
	
	@Override
	public Long zcard(byte[] key) {
		checkRead();
		
		try {
			return readJedis.zcard(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<String> zrange(String key, int start, int end) {
		checkRead();
		
		try {
			return readJedis.zrange(key, start, end);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}
	
	
	@Override
	public Set<byte[]> zrange(byte[] key, int start, int end) {
		checkRead();
		
		try {
			return readJedis.zrange(key, start, end);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}
	
	@Override
	public Set<byte[]> zrevrange(byte[] key, int start, int end) {
		checkRead();
		
		try {
			return readJedis.zrevrange(key, start, end);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		checkRead();
		
		try {
			return readJedis.zrangeByScore(key, min, max);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long zrank(String key, String member) {
		checkRead();
		
		try {
			return readJedis.zrank(key, member);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long zrem(String key, String member) {
		checkWrite();
		
		try {
			return writeJedis.zrem(key, member);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<String> zrevrange(String key, int start, int end) {
		checkRead();
		
		try {
			return readJedis.zrevrange(key, start, end);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		checkRead();
		
		try {
			return readJedis.zrevrangeByScore(key, max, min);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long zrevrank(String key, String member) {
		checkRead();
		
		try {
			return readJedis.zrevrank(key, member);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Double zscore(String key, String member) {
		checkRead();
		
		try {
			return readJedis.zscore(key, member);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}
	
	
	@Override
	public Double zscore(byte[] key, byte[] member) {
		checkRead();
		
		try {
			return readJedis.zscore(key, member);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Pipeline pipelined() {
		checkRead();
		
		try {
			return readJedis.pipelined();
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Set<String> sunion(String... keys) {
		checkRead();
		
		try {
			return readJedis.sunion(keys);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public void deletePtn4String(String pattern) {
		Set<String> set = this.keys(pattern);
		for(Iterator<String> it = set.iterator();it.hasNext();){
			String key = (String)it.next();
			this.del(key);
		}
	}
	@Override
	public Long hdel(String key, String field) {
		checkWrite();
		
		try{
			return writeJedis.hdel(key, field);
		}catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String rename(String oldkey, String newkey) {
		checkWrite();
		
		try{
			return  writeJedis.rename(oldkey,newkey);
		}catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}	
		return null;
	}

	@Override
	public Long move(String srckey, String dstkey, String member) {
		checkWrite();
		
		try {
			
			return writeJedis.smove(srckey, dstkey, member);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return -1L;
	}
	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		checkWrite();
		
		try {
			return writeJedis.zremrangeByScore(key, start, end);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}
	@Override
	public void del(byte[]... key) {
		checkWrite();
		
		try {
			writeJedis.del(key);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
	}

	@Override
	public String rpop(String listKey) {
		checkWrite();
		
		try {
			return writeJedis.rpop(listKey);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public String lindex(String listKey, Long index) {
		checkRead();
		
		try {
			return readJedis.lindex(listKey, index);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long llen(String key) {
		checkRead();
		
		try {
			return readJedis.llen(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long lpush(String key, String value) {
		checkWrite();
		
		try {
			return writeJedis.lpush(key, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long lrem(String key, int count, String value) {
		checkWrite();
		
		try {
			return writeJedis.lrem(key, count, value);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public List<String> lrange(String key, Long start, Long end) {
		checkRead();
		
		try {
			return readJedis.lrange(key, start, end);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}

	@Override
	public void mset(String... keyvalues) {
		checkWrite();
		
		try {
			writeJedis.mset(keyvalues);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
	}

	@Override
	public String rpoplpush(String srckey, String dstkey) {
		checkWrite();
		
		try {
			return writeJedis.rpoplpush(srckey,dstkey);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}

	@Override
	public Long sort(String key, SortingParams sortingParameters, String dstkey) {
		checkWrite();
		
		try {
			return writeJedis.sort(key, sortingParameters,dstkey);
		} catch (JedisConnectionException ex) {
			writeJedisFactory.reset();
		}
		return null;
	}
	@Override
	public Long ttl(String key) {
		checkRead();
		
		try {
			return readJedis.ttl(key);
		} catch (JedisConnectionException ex) {
			readJedisFactory.reset();
		}
		return null;
	}
}
