package com.alanma.doraemon.utils.jedis;

import redis.clients.jedis.Jedis;

public class TestJedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// String result=jedis.set("notice", "键都丢了");
		// String value=jedis.get("notice");
		// System.out.println("======="+result);
		// System.out.println("======="+value);
		testZSet(jedis);

	}

	private static void testString(Jedis jedis) {
		System.out.println(jedis.set("hello", "world"));
		System.out.println(jedis.get("hello"));
		System.out.println(jedis.incr("counter"));
	}

	private static void testHash(Jedis jedis) {
		System.out.println(jedis.hset("myhash", "f1","v1"));
		System.out.println(jedis.hset("myhash", "f2","v2"));
		System.out.println(jedis.hgetAll("myhash"));
	}
	
	private static void testList(Jedis jedis) {
		System.out.println(jedis.rpush("mylist", "1"));
		System.out.println(jedis.rpush("mylist", "2"));
		System.out.println(jedis.rpush("mylist", "3"));
		System.out.println(jedis.lrange("mylist", 0,-1));
	}
	
	private static void testSet(Jedis jedis) {
		System.out.println(jedis.sadd("myset", "a"));
		System.out.println(jedis.sadd("myset", "b"));
		System.out.println(jedis.sadd("myset", "a"));
		System.out.println(jedis.smembers("myset"));
	}
	
	private static void testZSet(Jedis jedis) {
		System.out.println(jedis.zadd("myzset", 99,"tom"));
		System.out.println(jedis.zadd("myzset", 66,"peter"));
		System.out.println(jedis.zadd("myzset", 33,"james"));
		System.out.println(jedis.zrangeWithScores("myzset", 0,-1));
	}
	
	

}
