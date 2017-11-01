package com.alanma.doraemon.utils.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	private static HttpContext localContext = new BasicHttpContext();
	private static HttpClientContext context = HttpClientContext.adapt(localContext);

	public static void main(String[] args) throws Exception {
//		testPost();
		testGet();
	}

	private static void testPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			// 模拟表单
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("download",
					"http://xxx.xxx.xxx/xxxx/FileDownServlet?filename=808080001000116_20090610_20090611031159.txt"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8887/path-cp/notice/chkfdown");
			httpPost.setEntity(entity);

			// 将HttpClientContext传入execute()中
			CloseableHttpResponse response = httpClient.execute(httpPost, context);

			try {
				HttpEntity responseEntity = response.getEntity();
				System.out.println("=================:" + EntityUtils.toString(responseEntity));

			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}

	private static void testGet() throws Exception {
		CloseableHttpClient httpClient2 = HttpClients.createDefault();

		try {
			HttpGet httpGet = new HttpGet("http://192.168.2.20:8887/path-cp/notice/chkfdown?download=http://xxx.xxx.xxx/xxxx/FileDownServlet?filename=808080001000116_20090610_20090611031159.txt");

			// 设置相同的HttpClientContext
			CloseableHttpResponse response = httpClient2.execute(httpGet, context);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("=================:" +EntityUtils.toString(entity));
			} finally {
				response.close();
			}
		} finally {
			httpClient2.close();
		}
	}

}
