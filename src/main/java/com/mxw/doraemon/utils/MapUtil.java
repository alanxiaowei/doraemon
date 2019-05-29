package com.mxw.doraemon.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	/**
	 * 构建map
	 * 
	 * @param entry
	 * @return
	 */
	public static Map<String, String> toStrMap(String... entry) {
		Map<String, String> returnMap = new HashMap<String, String>();

		for (int i = 0; i < entry.length; i++) {
			returnMap.put(entry[i], entry[++i]);
		}

		return returnMap;
	}

	/**
	 * 构建Map
	 * 
	 * @param entry
	 * @return
	 */
	public static Map<String, Object> toMap(Object... entry) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		for (int i = 0; i < entry.length; i++) {
			returnMap.put((String) entry[i], entry[++i]);
		}

		return returnMap;
	}
}
