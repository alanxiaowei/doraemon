package com.alanma.doraemon.utils;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Msg {
	public static void main(String[] args) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", "hujin");
		paramMap.put("passwd", "hujin");
		paramMap.put("phone", "18210219964");
		paramMap.put("content", "999666");
		Map ret=sendPost("http://123.56.158.45/message/sms.http.php", paramMap, null, "application/x-www-form-urlencoded;charset=utf-8");
		System.out.println(ret.toString());
	}
	
	public static Map<String, Object> sendPost(String url, Map<String, String> paramMap, String charset, String contentType) {

		if (url == null) {
		}
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type", contentType == null ? "application/json;charset=utf-8" : contentType);
		// 创建参数队列
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		// 处理参数
		for (Entry<String, String> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			formparams.add(new BasicNameValuePair(key, value));

		}
		// return map
		Map<String, Object> retMap = new HashMap<String, Object>();
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, charset == null ? "UTF-8" : charset);
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
				}
				retMap.put("status", response.getStatusLine());
				retMap.put("entity", entity);
			} finally {
				closeConnect(response);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeConnect(httpclient);
		}

		return retMap;
	}
	
	private static void closeConnect(CloseableHttpResponse response) {

		try {
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void closeConnect(CloseableHttpClient httpclient) {

		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
