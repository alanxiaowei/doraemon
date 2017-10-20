package com.alanma.doraemon.utils.jedis;

import redis.clients.jedis.Jedis;

public class TestJedis {
	public static void main(String[] args) {
		Jedis jedis=new Jedis("127.0.0.1",6379);
		String result=jedis.set("notice", "键都丢了");
		String value=jedis.get("notice");
		System.out.println("======="+result);
		System.out.println("======="+value);
		
	}
}
