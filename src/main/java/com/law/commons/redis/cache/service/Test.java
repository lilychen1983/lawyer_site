package com.law.commons.redis.cache.service;

import java.util.Iterator;
import java.util.Set;

import com.law.commons.redis.cache.ICacheClient;






public class Test {
	public static void main(String[] args) {
		ICacheClient dynamicReadClient = new RedisClient();

		String value = dynamicReadClient.get("mpls_info");
//		
		dynamicReadClient.sadd("keys", "3333");
		dynamicReadClient.sadd("keys", "4444");
		dynamicReadClient.sadd("keys", "gggg");
		dynamicReadClient.sadd("keys", "mm");
		Set<String> set = dynamicReadClient.smembers("solarwinds_ipdb_ipdb");
////		dynamicReadClient.del("keys");
		System.out.println(dynamicReadClient.exists("keys"));
		for(Iterator<String> it = set.iterator();it.hasNext();){
			System.out.println(it.next());
		}
//		
		System.out.println(value);
		if (dynamicReadClient != null) {
			dynamicReadClient.release();
		}
//		
//		String name = "$CaoYong";
//		String names = "i am Caoyong ,you are "+name+"!!!";
//		System.out.println(names.replaceAll("(?i)\\$caoyong", "M"));
		
//		File file = 
//				new File("E:/logs/connector/info.log");
//		System.out.println(file.getName());
	}
}
