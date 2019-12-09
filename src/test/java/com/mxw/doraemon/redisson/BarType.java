package com.mxw.doraemon.redisson;

import java.util.concurrent.TimeUnit;

/**
 * The bar types.
 * 
 * @author liaoxuefeng
 */
public enum BarType {
	/**
	 * Type of 1 sec. Keep recent 1 hour.
	 */
	K_1_SEC(1, TimeUnit.SECONDS, 3600),

	/**
	 * Type of 1 min. Keep recent 24 hours.
	 */
	K_1_MIN(1, TimeUnit.MINUTES, 60 * 24),

	/**
	 * Type of 1 hour. Keep recent 30 days.
	 */
	K_1_HOUR(1, TimeUnit.HOURS, 24 * 30),

	/**
	 * Type of 1 day. Keep all.
	 */
	K_1_DAY(1, TimeUnit.DAYS, 365 * 30);

	/**
	 * Get time delta in milliseconds.
	 */
	public final long timedelta;

	/**
	 * Get max recent items for cache.
	 */
	public final int maxRecentItems;

	public long normalize(long ts) {
		return ts / timedelta * timedelta;
	}

	/**
	 * Get the oldest start time from ts.
	 * 
	 * @param normalizedTime
	 * @return timestamp.
	 */
	public long getOldestStartTime(long normalizedTime) {
		return normalizedTime - timedelta * maxRecentItems;
	}

	private BarType(long duration, TimeUnit unit, int maxRecentItems) {
		this.timedelta = unit.toMillis(duration);
		this.maxRecentItems = maxRecentItems;
	}
}