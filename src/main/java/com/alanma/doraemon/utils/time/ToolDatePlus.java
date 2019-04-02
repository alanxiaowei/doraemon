package com.alanma.doraemon.utils.time;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Created on 17/4/8 18:55 星期六.
 *
 * @author SeanDragon
 */
public final class ToolDatePlus {

    private ToolDatePlus() {
        throw new UnsupportedOperationException("我是工具类，别初始化我。。。");
    }

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

    private static final Map<String, DateTimeFormatter> DATE_TIME_FORMATTER_MAP = Maps.newConcurrentMap();

    public static DateTimeFormatter pattern(final String patternStr) {
        DateTimeFormatter result = DATE_TIME_FORMATTER_MAP.get(patternStr);
        if (result == null) {
            result = DateTimeFormatter.ofPattern(patternStr);
            DATE_TIME_FORMATTER_MAP.put(patternStr, result);
        }
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(DEFAULT_ZONE_ID);
		System.out.println(format(new Date(), "yyyyMMdd"));
		System.out.println(LocalDate.now().minusDays(1).atTime(00, 00,00).atZone(ZoneId.of("UTC")));
		System.out.println(LocalDate.now().minusDays(1).atTime(00, 00,00).atZone(ZoneId.of("Asia/Shanghai")));
		System.out.println(LocalDate.now().minusDays(1).atTime(00, 00,00).atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());
		System.out.println(LocalDate.now().minusDays(1).atTime(00, 00,00).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
		
//		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(endtime), ZoneId.systemDefault());
//		LocalDateTime localdatetime = zdt.toLocalDateTime();
//		localdatetime = localdatetime.minusDays(1);
//		long starttime = localdatetime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//		
		
		
	}
    
    
}