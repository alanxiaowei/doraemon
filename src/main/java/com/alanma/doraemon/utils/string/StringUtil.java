package com.alanma.doraemon.utils.string;

public class StringUtil {
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str))
			return true;
		return false;
	}
}
