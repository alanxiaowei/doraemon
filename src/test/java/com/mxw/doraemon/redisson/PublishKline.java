package com.mxw.doraemon.redisson;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

import java.util.Arrays;
import java.util.List;

/**
 * @program: doraemon
 * @description: publish
 * @author: AlanMa
 * @create: 2020-03-13 18:17
 */
public class PublishKline {

	String msg = "{\"high\":218379,\"low\":7666}";

	List<String> nodes = Arrays.asList("redis://127.0.0.1:6379");
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);

	void publish() {
		try {
			redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);
			RTopic topic1 = redisClient.getTopic("notification");
			for (int i = 0; i < 50; i++) {
				topic1.publishAsync(msg);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			redisClient.shutdown();
		}
	}

	public static void main(String[] args) {
		PublishKline publish = new PublishKline();
		publish.publish();
	}

}
