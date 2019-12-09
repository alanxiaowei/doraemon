package com.mxw.doraemon.redisson;

import com.mxw.doraemon.multhread.eg.ExecutorProcessPool;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: doraemon
 * @description: Redis限流
 * @author: AlanMa
 * @create: 2019-08-14 16:13
 */
public class LimitRequest {

	protected final static Logger logger = LoggerFactory.getLogger(LimitRequest.class);

	List<String> nodes = Arrays.asList("redis://192.168.2.139:7001", "redis://192.168.2.139:7003", "redis://192.168.2.139:7005", "redis://192.168.2.195:7002", "redis://192.168.2.195:7004", "redis://192.168.2.195:7006");
	// RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("single", nodes, null);
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("cluster", nodes, null);

	private final String key = "RequestLimite";
	private final int limiter = 8;

	String keyLock = "testLock";



	//服务启动的时候，先清一下 redis，防止 count 出错
	public void reload() {
		RMapCache<String, Integer> msgRateLimit = redisClient.getMapCache(key, IntegerCodec.INSTANCE);
		if (msgRateLimit.containsKey(key)) {
			msgRateLimit.delete();
		}
	}

	//该方法可以配合 mq，结果是 true 的话就 ack，false 的话就 reject
	public boolean handleMessage() {
		//分布式场景下的限流
		//String key = "msgRateLimiter:" + MsgConstants.MsgType.APP_PUSH[0];
		RMapCache<String, Integer> msgRateLimit = redisClient.getMapCache(key, IntegerCodec.INSTANCE);
		Integer count;

		RLock lock = redisClient.getLock(keyLock);
		try {

			boolean res = lock.tryLock(10_000, 100, TimeUnit.MILLISECONDS);
			if (res) {
				msgRateLimit.putIfAbsent(key, 0, 60L, TimeUnit.SECONDS);
				count = msgRateLimit.addAndGet(key, 1);
				System.out.println("get redis counter:{"+count+"}");
				if (count <= limiter) {
					//此处是你要执行的代码
					return true;
				}
				System.out.println("超过限流:{"+count+"}");
			} else {
				System.err.println("failed to get lock !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		LimitRequest limiter = new LimitRequest();
		limiter.reload();
		ExecutorProcessPool pool = ExecutorProcessPool.getInstanceCached();
		for (int i=0;i<10;i++){
			logger.info("第{}次请求你",i);
			final int reqTimes = i;
			pool.execute(()->{
				System.out.println(reqTimes+"\t"+limiter.handleMessage());
			});
		}

	}

}
