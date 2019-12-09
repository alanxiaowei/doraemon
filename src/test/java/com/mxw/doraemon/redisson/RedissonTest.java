package com.mxw.doraemon.redisson;

import com.alibaba.fastjson.JSONObject;
import org.redisson.api.*;
import org.redisson.client.protocol.ScoredEntry;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedissonTest {
	// List<String> nodes = Arrays.asList("redis://127.0.0.1:6379");

	List<String> nodes = Arrays.asList("redis://192.168.2.139:7001", "redis://192.168.2.139:7003", "redis://192.168.2.139:7005", "redis://192.168.2.195:7002", "redis://192.168.2.195:7004", "redis://192.168.2.195:7006");
	// RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("cluster", nodes, null);

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

		Collection<Long> collValues = set.valueRange(0, 2);
		collValues.forEach(value -> {
			System.out.println("\t Value:" + value);
		});

	}

	void getScoreAndRank() {
		RScoredSortedSet<Long> set = redisClient.getScoredSortedSet("user:volume:20180529");
		System.out.println(set.getScore(10009L));
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
		// UserVolume uv = new UserVolume();
		// uv.setUserId(1000008L);
		// RMap<String, String> lastTicks = redisClient.getMap("hash:user:volume:222");
		// lastTicks.put("166666", JSONObject.toJSONString(uv));
		// UserVolume user1 = JSONObject.parseObject(lastTicks.get("166666"), UserVolume.class);
		// System.out.println(user1.toString());
		RMap<String, BigDecimal> big = redisClient.getMap("test:bigdecimal");
		big.put("aaa", new BigDecimal(10.88));
		RMap<String, BigDecimal> big2 = redisClient.getMap("test:bigdecimal");
		BigDecimal amt = big2.get("aaa");
		System.out.println("~~~~~~~~~~:"+amt);
	}

	void testExpire() {
		RMap<String, String> expire = redisClient.getMap("expire");
		expire.put("111111", "i love you 3k times");
		expire.expire(10, TimeUnit.SECONDS);
	}

	void testBloom() {
		RBloomFilter<User> bloomFilter = redisClient.getBloomFilter("sample:user1");
		System.out.println("~~~~~~~~~~~~:" + bloomFilter.isExists());
		// 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
		bloomFilter.tryInit(100_0000L, 0);
		bloomFilter.add(new User("alan", "bj"));
		bloomFilter.add(new User("kitty", "sh"));
		bloomFilter.expireAt(System.currentTimeMillis() + 30 * 1000L);
		boolean isRepeat = bloomFilter.contains(new User("alan", "bj"));
		System.out.println(isRepeat);

	}


	void testReentrantLock() {
		RLock lock = redisClient.getLock("userinfolock");
		try {
			// 1. 最常见的使用方法
			// lock.lock();
			// 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
			// lock.lock(3, TimeUnit.SECONDS);
			// 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
			boolean res = lock.tryLock(3, 30, TimeUnit.SECONDS);
			if (res) { //成功
				// do your business
				System.out.println("~~~lock  successfully~");
			} else {
				System.out.println("~~~lock  failure ~");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlinkAsync();
		}
	}

	void testUsr() {
		RMap<String, String> userCache = redisClient.getMap("user:test");
		User user = new User("alan", "bj");
		userCache.put("0001", JSONObject.toJSONString(user));
	}

	void updateUsr() {
		RMap<String, String> userCache = redisClient.getMap("user:test");
		User user = JSONObject.parseObject(userCache.get("0001"), User.class);
		user.setAddress("sh");
		userCache.put("0001", JSONObject.toJSONString(user));
	}

	void testZsetAdd() {
		RScoredSortedSet<String> set = redisClient.getScoredSortedSet("user:info:zset");
		User user = new User("Mike", "USA");
		set.add(10001, JSONObject.toJSONString(user));
		// RScoredSortedSet<String> set=redisClient.getScoredSortedSet("XXXXX");
		// System.out.println(set.getScore("100"));


		Collection<String> users = set.valueRange(set.firstScore(), true, 10003, true);

		users.forEach(userstr -> System.out.println(userstr));
		//
		// for (String str : users) {
		// 	System.out.println(str);
		// }
		//
		// System.out.println(users.iterator().next());
	}


	private void testSortedSet() {
		RScoredSortedSet<String> set = redisClient.getScoredSortedSet("last_bar_secXXX_YYY");

		set.addAsync(1,"testbyalanma222" );

		// if(set.size()==0){
		// 	System.out.println("skip");
		// }else{
		// 	Collection<String> bars = set.valueRange(set.firstScore(), true, 1560740625702L, true);
		//
		// }
	}



	public static void main(String[] args) {
		RedissonTest test = new RedissonTest();
		// test.testZSET();
		// test.printZSET();
		// test.testNullKey();
		// test.getScoreAndRank();
		// test.testBigDecimal();
		// test.testExpire();
		// test.testBloom();
		// test.testUsr();
		// test.testReentrantLock();
		// test.updateUsr();
		// test.testZsetAdd();
		test.testSortedSet();
	}



}
