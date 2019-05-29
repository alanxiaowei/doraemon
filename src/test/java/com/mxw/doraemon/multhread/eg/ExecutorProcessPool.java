package com.mxw.doraemon.multhread.eg;

import java.util.concurrent.*;

/**
 * 线程处理类
 *
 * @author AlanMa
 * @date 2016年9月5日
 */
public class ExecutorProcessPool {

	private final int threadMax = 10;

	/**
	 * 线程池实例
	 */
	private ExecutorService executor;

	/**
	 * 延迟、定时周期任务线程池
	 */
	private volatile static ExecutorProcessPool poolScheduled;

	/**
	 * 单线程线程池
	 */
	private volatile static ExecutorProcessPool poolSingle;

	/**
	 * 可缓存线程池，无上限，空闲线程优先执行
	 */
	private volatile static ExecutorProcessPool poolCached;

	/**
	 * 可控制线程最大并发数线程池
	 */
	private volatile static ExecutorProcessPool poolFixed;

	private ExecutorProcessPool() {
	}

	private ExecutorProcessPool(ExecutorService executor) {
		super();
		this.executor = executor;
	}

	public static ExecutorProcessPool getInstanceScheduled() {
		if (poolScheduled == null) {
			synchronized (ExecutorProcessPool.class) {
				if (poolScheduled == null) {
					poolScheduled = new ExecutorProcessPool(
							ExecutorServiceFactory.getInstance().createScheduledThreadPool());
				}
			}
		}
		return poolScheduled;
	}

	public static ExecutorProcessPool getInstanceSingle(ExecutorService executor) {
		if (poolSingle == null) {
			synchronized (ExecutorProcessPool.class) {
				if (poolSingle == null) {
					poolSingle = new ExecutorProcessPool(
							ExecutorServiceFactory.getInstance().createSingleThreadExecutor());
				}
			}
		}
		return poolSingle;
	}

	public static ExecutorProcessPool getInstanceCached() {
		if (poolCached == null) {
			synchronized (ExecutorProcessPool.class) {
				if (poolCached == null) {
					poolCached = new ExecutorProcessPool(ExecutorServiceFactory.getInstance().createCachedThreadPool());
				}
			}
		}
		return poolCached;
	}

	public static ExecutorProcessPool getInstanceFixed(int threadPoolMax) {
		if (poolFixed == null) {
			synchronized (ExecutorProcessPool.class) {
				if (poolFixed == null) {
					poolFixed = new ExecutorProcessPool(
							ExecutorServiceFactory.getInstance().createFixedThreadPool(threadPoolMax));
				}
			}
		}
		return poolFixed;
	}

	/**
	 * 关闭线程池，这里要说明的是：调用关闭线程池方法后，线程池会执行完队列中的所有任务才退出
	 * 
	 * @author SHANHY
	 * @date 2015年12月4日
	 */
	public void shutdown() {
		executor.shutdown();
	}

	/**
	 * 提交任务到线程池，可以接收线程返回值
	 * 
	 * @param task
	 * @return
	 * @author SHANHY
	 * @date 2015年12月4日
	 */
	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}

	public void scheduleAtFixedRate(Runnable task, long initialDelay, long period) {
		((ScheduledExecutorService) executor).scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
	}

	/**
	 * 提交任务到线程池，可以接收线程返回值
	 * 
	 * @param task
	 * @return
	 * @author SHANHY
	 * @date 2015年12月4日
	 */
	public Future<?> submit(Callable<?> task) {
		return executor.submit(task);
	}

	/**
	 * 直接提交任务到线程池，无返回值
	 * 
	 * @param task
	 * @author SHANHY
	 * @date 2015年12月4日
	 */
	public void execute(Runnable task) {
		executor.execute(task);
	}

}