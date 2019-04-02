package com.alanma.doraemon.utils.redisson;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;

public class RedissonTest {
	List<String> nodes = Arrays.asList("redis://127.0.0.1:6379");
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);

	void testZSET() {
		RScoredSortedSet<Long> set = redisClient.getScoredSortedSet("user:volume:20180529");
		// set.add(100, 10000L);
		// set.addAsync(101, 10001L);
		// set.add(123, 10002L);
		// set.add(234, 10003L);
		// set.add(432, 10004L);
		// set.add(567, 10005L);

	}

	void printZSET() {
		RScoredSortedSet<Long> set = redisClient.getScoredSortedSet("user:volume:20180529");
		Collection<ScoredEntry<Long>> coll = set.entryRange(0, -1);
		coll.forEach(entry -> {
			System.out.println("Score:" + entry.getScore() + "\t Value:" + entry.getValue());
		});
	}
	
	void testNullKey(){
		RMap<String, String> lastTicks = redisClient.getMap("EEEEXXXX");
		System.out.println("=========:"+lastTicks.toString());
		System.out.println("=========:"+lastTicks.get("ETH"));
	}

	public static void main(String[] args) {
		RedissonTest test = new RedissonTest();
		// test.testZSET();
//		test.printZSET();
		test.testNullKey();
	}
}
