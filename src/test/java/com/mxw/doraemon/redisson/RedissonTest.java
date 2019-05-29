package com.mxw.doraemon.redisson;

import com.alibaba.fastjson.JSONObject;
import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedissonTest {
	List<String> nodes = Arrays.asList("redis://127.0.0.1:6379");
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);

	void testZSET() {
		RScoredSortedSet<Long> set = redisClient.getScoredSortedSet("user:volume:20180529");
		set.add(300, 10003L);

		// set.addAsync(101, "");
		// set.add(123, 10002L);
		// set.add(234, 10003L);
		// set.add(432, 10004L);
		// set.add(567, 10005L);

	}

	void printZSET() {
		RScoredSortedSet<Long> set = redisClient.getScoredSortedSet("user:volume:20180529");
		// asc
		// Collection<ScoredEntry<Long>> coll = set.entryRange(0, -1);
		Collection<ScoredEntry<Long>> coll = set.entryRange(0, 2);
		coll.forEach(entry -> {
			System.out.println("Score:" + entry.getScore() + "\t Value:" + entry.getValue());
		});
	}

	void getScoreAndRank() {
		RScoredSortedSet<Long> set = redisClient.getScoredSortedSet("user:volume:20180529");
		System.out.println(set.getScore(10003L));
		System.out.println(set.rank(10003L));
	}

	void testNullKey() {
		RMap<String, String> lastTicks = redisClient.getMap("EEEEXXXX");
		System.out.println("=========:" + lastTicks.toString());
		System.out.println("=========:" + lastTicks.get("ETH"));
	}

	void testHash() {
		RMap<String, String> lastTicks = redisClient.getMap("hash:user:volume:111");
		// User user = new User("Alan", "Beijing");
		// lastTicks.put("100006", JSONObject.toJSONString(user));
//		User user1 = JSONObject.parseObject(lastTicks.get("100006"), User.class);
		// System.out.println("=========:" + lastTicks.toString());
		// System.out.println("=========:" + lastTicks.get("100006"));
		// System.out.println("=========:" + user1.getName());
		// System.out.println("=========:" + user1.getAddress());

		
		System.out.println(lastTicks.get("111"));

	}

	void testBigDecimal() {
		UserVolume uv = new UserVolume();
		uv.setUserId(1000008L);
		RMap<String, String> lastTicks = redisClient.getMap("hash:user:volume:222");
		lastTicks.put("166666", JSONObject.toJSONString(uv));
		UserVolume user1 = JSONObject.parseObject(lastTicks.get("166666"), UserVolume.class);
		System.out.println(user1.toString());
	}
	
	void testExpire(){
		RMap<String, String>  expire=redisClient.getMap("expire");
		expire.put("111111", "i love you 3k times");
		expire.expire(10, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		RedissonTest test = new RedissonTest();
		// test.testZSET();
		// test.printZSET();
		// test.testNullKey();
		// test.getScoreAndRank();
		// test.testBigDecimal();
		test.testExpire();
	}
}
