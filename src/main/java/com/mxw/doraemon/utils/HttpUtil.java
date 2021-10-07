package com.mxw.doraemon.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class HttpUtil {

	final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String getReferer(HttpServletRequest request) {
		String url = request.getHeader("Referer");
		return url == null ? "/" : url;
	}

	public static String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip != null && !ip.isEmpty()) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (ip != null && !ip.isEmpty()) {
			int pos = ip.indexOf(',');
			if (pos == -1) {
				return ip;
			}
			return ip.substring(0, pos);
		}
		ip = request.getRemoteAddr();
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}
	final static int MAX_UNIQUE_ID_LENGTH = 40;

	/**
	 * 获取payload
	 * @param request
	 * @param headers
	 * @param body
	 * @return
	 */
	private static String getPayload(HttpServletRequest request, List<String> headers, String body) {

		StringBuilder sb = new StringBuilder();
		// METHOD
		sb.append(request.getMethod().toUpperCase()).append('\n');
		// host
		sb.append(request.getServerName().toLowerCase()).append('\n');
		// URI
		sb.append(request.getRequestURI()).append('\n');

		List<String> params = new ArrayList<>();
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String key = en.nextElement();
			String[] values = request.getParameterValues(key);
			for (String value : values) {
				params.add(key + "=" + value);
			}
		}
		Collections.sort(params);
		sb.append(String.join("&", params)).append('\n');

		Collections.sort(headers);
		for (String header : headers) {
			sb.append(header).append('\n');
		}
		if (body != null && body.length() > 0) {
			sb.append(body);
		}
		String payload = sb.toString();
		return payload;
	}
}
