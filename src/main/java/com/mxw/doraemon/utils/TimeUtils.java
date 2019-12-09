package com.mxw.doraemon.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeUtils {
	public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static volatile ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

	public static LocalDateTime date2LocalDateTime(final Date date) {
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(DEFAULT_ZONE_ID);
		return zdt.toLocalDateTime();
	}

	public static LocalDateTime time2LocalDateTime(final long time) {
		return date2LocalDateTime(new Date(time));
	}

	public static Date localDateTime2Date(final LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(DEFAULT_ZONE_ID).toInstant();
		return Date.from(instant);
	}

	public static long localDateTime2time(final LocalDateTime localDateTime) {
		return Timestamp.valueOf(localDateTime).getTime();
	}

	public static String format(final Date date, final String pattern) {
		return date2LocalDateTime(date).format(DateTimeFormatter.ofPattern(pattern));
	}

	public static ZoneId getDefaultZoneId() {
		return DEFAULT_ZONE_ID;
	}

	public static void setDefaultZoneId(ZoneId defaultZoneId) {
		DEFAULT_ZONE_ID = defaultZoneId;
	}

	/**
	 * 返回当前的日期
	 *
	 * @return
	 */
	public static LocalDate getCurrentLocalDate() {
		return LocalDate.now();
	}

	/**
	 * 返回当前日期时间
	 *
	 * @return
	 */
	public static LocalDateTime getCurrentLocalDateTime() {
		return LocalDateTime.now();
	}

	/**
	 * 返回当前日期字符串 yyyyMMdd
	 *
	 * @return
	 */
	public static String getCurrentDateStr() {
		return LocalDate.now().format(DATE_FORMATTER);
	}

	public static String parseLocDateTimeToString(LocalDateTime time) {
		return time.format(DATE_FORMATTER);
	}

	/**
	 * 返回当前日期时间字符串 yyyyMMddHHmmss
	 *
	 * @return
	 */
	public static String getCurrentDateTimeStr() {
		return LocalDateTime.now().format(DATETIME_FORMATTER);
	}

	public static LocalDate parseLocalDate(String dateStr, String pattern) {
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
	}

	public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
		return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 日期相隔天数
	 *
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return
	 */
	public static int periodDays(LocalDate startDateInclusive, LocalDate endDateExclusive) {
		return Period.between(startDateInclusive, endDateExclusive).getDays();
	}

	/**
	 * 日期相隔小时
	 *
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
		return Duration.between(startInclusive, endExclusive).toHours();
	}

	/**
	 * 日期相隔分钟
	 *
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}

	/**
	 * 日期相隔毫秒数
	 *
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
		return Duration.between(startInclusive, endExclusive).toMillis();
	}

	/**
	 * 是否当天
	 *
	 * @param date
	 * @return
	 */
	public static boolean isToday(LocalDate date) {
		return getCurrentLocalDate().equals(date);
	}

	/**
	 * 获取本月的第一天
	 *
	 * @return
	 */
	public static String getFirstDayOfThisMonth() {
		return getCurrentLocalDate().with(TemporalAdjusters.firstDayOfMonth()).format(DATE_FORMATTER);
	}

	/**
	 * eg:2017-01-06
	 *
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfThisMonth(String month) {
		return LocalDate.parse(month).with(TemporalAdjusters.firstDayOfMonth()).format(DATE_FORMATTER);
	}

	/**
	 * 获取本月的最后一天
	 *
	 * @return
	 */
	public static String getLastDayOfThisMonth() {
		return getCurrentLocalDate().with(TemporalAdjusters.lastDayOfMonth()).format(DATE_FORMATTER);
	}

	/**
	 * eg:2017-01-06
	 *
	 * @param month
	 * @return
	 */
	public static String getLastDayOfThisMonth(String month) {
		return LocalDate.parse(month).with(TemporalAdjusters.lastDayOfMonth()).format(DATE_FORMATTER);
	}

	/**
	 * 获取2017-01的第一个周一
	 *
	 * @return
	 */
	public static String getFirstMonday() {
		return LocalDate.parse("2017-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
				.format(DATE_FORMATTER);
	}

	/**
	 * 获取当前日期的后两周
	 *
	 * @return
	 */
	public static String getCurDateAfterTwoWeek() {
		return getCurrentLocalDate().plus(2, ChronoUnit.WEEKS).format(DATE_FORMATTER);
	}

	/**
	 * 获取当前日期的6个月后的日期
	 *
	 * @return
	 */
	public static String getCurDateAfterSixMonth() {
		return getCurrentLocalDate().plus(6, ChronoUnit.MONTHS).format(DATE_FORMATTER);
	}

	/**
	 * 获取当前日期的5年后的日期
	 *
	 * @return
	 */
	public static String getCurDateAfterFiveYear() {
		return getCurrentLocalDate().plus(5, ChronoUnit.YEARS).format(DATE_FORMATTER);
	}

	/**
	 * 获取当前日期的20年后的日期
	 *
	 * @return
	 */
	public static String getCurDateAfterTwentyYear() {
		return getCurrentLocalDate().plus(2, ChronoUnit.DECADES).format(DATE_FORMATTER);
	}

	// Date转换为LocalDateTime
	public static LocalDateTime convertDateToLDT(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	// LocalDateTime转换为Date
	public static Date convertLDTToDate(LocalDateTime time) {
		return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
	}

	// 获取指定日期的毫秒
	public static Long getMilliByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	// 获取指定日期的秒
	public static Long getSecondsByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	// 获取指定时间的指定格式
	public static String formatTime(LocalDateTime time, String pattern) {
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}

	// 获取当前时间的指定格式
	public static String formatNow(String pattern) {
		return formatTime(LocalDateTime.now(), pattern);
	}

	// 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
	public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
		return time.plus(number, field);
	}

	// 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
	public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
		return time.minus(number, field);
	}

	/**
	 * 获取两个日期的差 field参数为ChronoUnit.*
	 *
	 * @param startTime
	 * @param endTime
	 * @param field     单位(年月日时分秒)
	 * @return
	 */
	public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
		Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
		if (field == ChronoUnit.YEARS)
			return period.getYears();
		if (field == ChronoUnit.MONTHS)
			return period.getYears() * 12 + period.getMonths();
		return field.between(startTime, endTime);
	}

	// 获取一天的开始时间，2017,7,22 00:00
	public static LocalDateTime getDayStart(LocalDateTime time) {
		return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	// 获取一天的结束时间，2017,7,22 23:59:59.999999999
	public static LocalDateTime getDayEnd(LocalDateTime time) {
		return time.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
	}

	// 获取一天的开始时间，2017,7,22 00:00
	public static long getDayStart(String time, String pattern) {
		LocalDateTime ldtTrans = parseLocalDateTime(time, pattern);
		LocalDateTime ldt = ldtTrans.withHour(0).withMinute(0).withSecond(0).withNano(0);
		return getMilliByTime(ldt);
	}

	// 获取一天的结束时间，2017,7,22 23:59:59.999999999
	public static long getDayEnd(String time, String pattern) {
		LocalDateTime ldtTrans = parseLocalDateTime(time, pattern);
		LocalDateTime ldt = ldtTrans.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
		return getMilliByTime(ldt);
	}

	// 获取一天的开始UTC时间，2017,7,22 00:00
	public static Long getUTCDayStart(Long timestamp) {
		LocalDateTime time = convertDateToLDT(new Date(timestamp));
		LocalDateTime ldt = time.withHour(8).withMinute(0).withSecond(0).withNano(0);
		return getMilliByTime(ldt);
	}

	// 获取一天的结束UTC时间，2017,7,22 23:59:59.999999999
	public static Long getUTCDayEnd(Long timestamp) {
		LocalDateTime time = convertDateToLDT(new Date(timestamp));
		LocalDateTime ldt = time.plus(1, ChronoUnit.DAYS).withHour(7).withMinute(59).withSecond(59).withNano(999999999);
		return getMilliByTime(ldt);
	}

	// 获取一天的开始UTC时间，2017,7,22 00:00
	public static Long getUTCDayStart(LocalDateTime time) {
		LocalDateTime ldt = time.withHour(8).withMinute(0).withSecond(0).withNano(0);
		return getMilliByTime(ldt);
	}

	// 获取一天的结束UTC时间，2017,7,22 23:59:59.999999999
	public static Long getUTCDayEnd(LocalDateTime time) {
		LocalDateTime ldt = time.plus(1, ChronoUnit.DAYS).withHour(7).withMinute(59).withSecond(59).withNano(999999999);
		return getMilliByTime(ldt);
	}

	public static Long getUTCLongByString(String time, String pattern) {
		LocalDateTime ldtTrans = parseLocalDateTime(time, pattern);
		return getMilliByTime(ldtTrans);
	}

	public static List<LocalDateTime> getPeriodDays(Long start, Long end) {
		List<LocalDateTime> list = new ArrayList<LocalDateTime>();
		LocalDateTime starttime = convertDateToLDT(new Date(start));
		LocalDateTime endtime = convertDateToLDT(new Date(end));
		LocalDateTime tmpTime = starttime;

		while (tmpTime.isBefore(endtime)) {
			list.add(tmpTime);
			tmpTime = tmpTime.plusDays(1);
		}

		return list;
	}

	//public static List<LocalDateTime> getPeriodDaysAddOneDay(Long start, Long end) {
	//    List<LocalDateTime> list=new ArrayList<LocalDateTime>();
	//    LocalDateTime starttime = convertDateToLDT(new Date(start));
	//    LocalDateTime endtime = convertDateToLDT(new Date(end));
	//    LocalDateTime tmpTime=starttime;
	//
	//    while (tmpTime.isBefore(endtime)) {
	//        list.add(tmpTime);
	//        tmpTime=tmpTime.plusDays(1);
	//    }
	//    list.add(tmpTime);
	//
	//    return list;
	//}

	public static List<Long> getPeriodDaysAddOneDay(Long start, Long end) {
		List<Long> list = new ArrayList<Long>();
		LocalDateTime starttime = convertDateToLDT(new Date(start));
		LocalDateTime endtime = convertDateToLDT(new Date(end));
		LocalDateTime tmpTime = starttime;

		while (tmpTime.isBefore(endtime)) {
			list.add(localDateTime2time(tmpTime));
			tmpTime = tmpTime.plusDays(1);
		}
		list.add(localDateTime2time(tmpTime));

		return list;
	}

	/**
	 * 获取月份
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		LocalDateTime time = date2LocalDateTime(date);
		return time.getMonth().getValue();
	}

	/**
	 * 获取日期
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		LocalDateTime time = date2LocalDateTime(date);
		return time.getDayOfMonth();
	}

	public static String parseTimeLongToString(Long time) {
		return parseLocDateTimeToString(time2LocalDateTime(time));
	}

	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static Long getDayStartTimeStamp(){
		LocalDateTime startTime=TimeUtils.getDayStart(getCurrentLocalDateTime());
		return localDateTime2time(startTime);
	}

	/**
	 * 获取当前
	 * @return
	 */
	public static Long getCurrentTimeStamp(){
		return localDateTime2time(getCurrentLocalDateTime());
	}

	public static void main(String[] args) {
		// LocalDateTime time = parseLocalDateTime("2018-02-28 08:00:00", "yyyy-MM-dd
		// HH:mm:ss");
		// System.out.println("time:" + time);
		// Long ms = getMilliByTime(time);
		// System.out.println("ms:" + ms);
		// time = time.plus(1, ChronoUnit.DAYS);
		// System.out.println("time + 1 DAY:" + time);
		// System.out.println("ms + 1 DAY:" + getMilliByTime(time));
		// String timeStr = parseTimeLongToString(glocalDateTime.plusHours(1L)etMilliByTime(time));
		// System.out.println(timeStr);
		// =========
		//System.out.println(getUTCDayStart(1536512642779L));
		//System.out.println(getUTCDayStart(1536512642779L));
		//System.out.println(getFirstDayOfThisMonth("2017-02-08"));
		//System.out.println(getLastDayOfThisMonth("2017-02-08"));
		//System.out.println(parseLocalDateTime("2017-02-08 00:00:00", "yyyy-MM-dd HH:mm:ss"));

		//Long time = getUTCLongByString("2018-07-10 00:01:00", "yyyy-MM-dd HH:mm:ss");
		//System.out.println(time);
		//
		//LocalDateTime localDateTimetime=getCurrentLocalDateTime();
		//System.out.println(localDateTimetime);

		// LocalDateTime localDateTime = time2LocalDateTime(1536512642779L);
		// System.out.println(localDateTime);
		// System.out.println(localDateTime.plusHours(1L));
		// System.out.println(localDateTime2time(localDateTime.plusHours(1L)));

		Date date = new Date();
		System.out.println(date);

	}


}