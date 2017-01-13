package com.alanma.doraemon.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * http请求类
 * @author zy
 *
 */
public class HttpClientUtil {
	
	Logger logger  = Logger.getLogger(HttpClientUtil.class);
	
	public static void main(String[] args) throws Exception {
		
		String requestUrl = "http://120.24.222.237:7001/bioauth-face-ws/face/compareFaces";
		
		String pic="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";
		
		JSONObject paramMap = new JSONObject();

//		String img1 = Base64Util.GetImageStr("c://test1.jpg");
//		String img2 = Base64Util.GetImageStr("c://test1.jpg");

		paramMap.put("img1", pic);
		paramMap.put("img2", pic);
		
		String sss = new HttpClientUtil().post(requestUrl,paramMap);
		System.out.println(sss);
	}
	
	/**
	 * post请求
	 * @param requestUrl
	 * @param paramMap
	 */
	public String  post(String requestUrl,JSONObject paramMap){
		HttpClient client = null;
		try {
			// 创建客户端实例
			client = new DefaultHttpClient();
			// 设置请求参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("params", paramMap.toString()));
			// 绑定请求
			HttpPost post = new HttpPost(requestUrl);
			System.out.println("paramMap---->"+nvps.toString());
			// 设置请求参数
			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

			// 进行请求
			HttpResponse httpResponse = client.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 return EntityUtils.toString(httpResponse.getEntity());
				 
			} else {
				logger.error("http异常返回值："+httpResponse.getStatusLine().getStatusCode());
				return "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return null;
	}
}
