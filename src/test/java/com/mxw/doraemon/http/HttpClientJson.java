package com.mxw.doraemon.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class HttpClientJson {
	private static final String APPLICATION_JSON = "application/json";

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	public static void httpPostWithJSON(String url, String json) throws Exception {
		// 将JSON进行UTF-8编码,以便传输中文
		String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

		StringEntity se = new StringEntity(encoderJson);
		se.setContentType(CONTENT_TYPE_TEXT_JSON);
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
		httpPost.setEntity(se);
		CloseableHttpResponse resp = httpClient.execute(httpPost);

		System.out.println("==============:" + resp.getStatusLine());

		HttpEntity entity2 = resp.getEntity();
		String result = new BufferedReader(new InputStreamReader(entity2.getContent(), "utf-8")).lines()
				.collect(Collectors.joining(System.lineSeparator()));

		System.out.println("==============:" + result);
		// do something useful with the response body
		// and ensure it is fully consumed
		// 消耗掉response
		EntityUtils.consume(entity2);
		resp.close();
	}

	public static String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {

		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		// 将资料解码
		String reqBody = sb.toString();
		return URLDecoder.decode(reqBody, HTTP.UTF_8);
	}

	public static void main(String[] args) {
		String url = "http://star-mars.8.163.com/api/btcdo/orderNotice";
		String param = "{\"appId\":\"S00263806\",\"sign\":\"OsAPj6wSYD/pJCsCpMCYGuzXR4GLpFIdr74yevJmcMbnzp6zImF2sJPowRz703F0gyB2ZKyiCvNOEun5sZyqgbvs6YvxYBp7N3xRJuMekY7L3NdxsCXSHua16fzDCy1kUoRPcuBAsB5oqHHoEXmXx3wHE6kXXp8cOVH19zABoKY=\"}";

		try {
			httpPostWithJSON(url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end========");
	}
}
