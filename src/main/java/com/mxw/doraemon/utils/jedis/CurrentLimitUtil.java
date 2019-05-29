package com.mxw.doraemon.utils.jedis;

import com.mxw.doraemon.utils.multhread.pool.TestThreadPoolProcessor;
import redis.clients.jedis.Jedis;

public class CurrentLimitUtil {
	public static String currentLimit(String key){
		Jedis jedis = JedisPoolUtils.getJedis();
		String countPerSec=jedis.get("clmxw001");
		if(countPerSec==null){
			jedis.setex(key, 10, "1");
			return "1";
		}else if(Integer.parseInt(countPerSec)<15){
			return Long.toString(jedis.incr(key));
		}else{
			return "-1";
		}
	}
	
	public static void main(String[] args) {
		TestThreadPoolProcessor pool = new TestThreadPoolProcessor();
		for (int i = 1; i <= 20; i++) {
			System.out.print(CurrentLimitUtil.currentLimit("clmxw001") + "|");
			pool.threadPool();
		}
	}
}
