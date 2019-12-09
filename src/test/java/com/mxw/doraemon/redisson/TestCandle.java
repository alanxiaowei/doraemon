package com.mxw.doraemon.redisson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mxw.doraemon.utils.TimeUtils;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: doraemon
 * @description: K线测试
 * @author: AlanMa
 * @create: 2019-06-05 14:56
 */
public class TestCandle {
	List<String> nodes = Arrays.asList("redis://192.168.2.139:7001");
	RedissonClient redisClient = RedisClientBuilder.buildRedissionClient("cluster", nodes, null);


	Bar genDayK() {
		//UTC 0点——now 的分钟K
		Long start = TimeUtils.getDayStartTimeStamp();
		Long end = TimeUtils.getCurrentTimeStamp();
		RScoredSortedSet<String> set = redisClient.getScoredSortedSet("BTC_USDTK_1_MIN");
		Collection<String> coll = set.valueRange(start, true, end, true);

		List<Bar> list = coll.stream().map(v -> {
			JSONArray array=JSONObject.parseArray(v);
			Bar bar =new Bar();
			bar.startTime=array.getLong(0);
			bar.openPrice=array.getBigDecimal(1);
			bar.highPrice = array.getBigDecimal(2);
			bar.lowPrice = array.getBigDecimal(3);
			bar.closePrice = array.getBigDecimal(4);
			bar.amount = array.getBigDecimal(5);
			bar.type=BarType.K_1_MIN;
			return bar;
		}).collect(Collectors.toList());

		//根据分钟K生成日K
		Bar barDay = new Bar();
		barDay.startTime=start;
		barDay.openPrice=list.get(0).openPrice;
		barDay.highPrice=new BigDecimal(0);
		barDay.lowPrice=list.get(0).lowPrice;
		barDay.closePrice=list.get(0).closePrice;
		barDay.amount=new BigDecimal(0);
		barDay.type=BarType.K_1_DAY;
		barDay.symbol="BTC_USDT";//TODO

		list.stream().forEach(x->{
			barDay.merge(x);
		});

		System.out.println(barDay.toString());

		return barDay;

	}

	public static void main(String[] args) {
		TestCandle test = new TestCandle();
		Bar bar=test.genDayK();
		System.out.println(bar.toString());
		JSONArray array = new JSONArray();
		array.add(bar.startTime);
		array.add(bar.type);
		array.add(bar.openPrice);
		array.add(bar.highPrice);
		array.add(bar.lowPrice);
		array.add(bar.closePrice);
		array.add(bar.amount);
		System.out.println("====:"+array.toJSONString());
		test.redisClient.shutdown();

	}
}
