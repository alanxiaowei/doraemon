package com.mxw.doraemon.map;

import com.mxw.doraemon.redisson.User;
import com.mxw.doraemon.utils.MapUtil;

import java.util.Map;

/**
 * @program: doraemon
 * @description: 对象转Map
 * @author: AlanMa
 * @create: 2019-12-05 17:26
 */
public class ObjectToMap {


	public static void main(String[] args) {
		User user = new User();
		user.setAddress("Beijing");
		user.setAge(18);
		user.setName("Alan");

		Map map = MapUtil.beanToMap(user);
		System.out.println(map.toString());
	}

}
