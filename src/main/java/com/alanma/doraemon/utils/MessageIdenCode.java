package com.alanma.doraemon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.alanma.doraemon.utils.http.HttpClientUtils;
import com.alibaba.dubbo.common.utils.StringUtils;

public class MessageIdenCode {

	private static void sendMsg(String url, String username, String password, String phoneNum, String content)
			throws UnsupportedEncodingException {

		StringBuffer reqContent = new StringBuffer();
		reqContent.append("&");
		reqContent.append("CorpID=");
		reqContent.append(username);
		reqContent.append("&");
		reqContent.append("Pwd=");
		reqContent.append(password);
		reqContent.append("&");
		reqContent.append("Mobile=");
		reqContent.append(phoneNum);
		reqContent.append("&");
		reqContent.append("Content=");
		reqContent.append(new String(content.getBytes("gb2312"), "gb2312"));
		reqContent.append("&");
		reqContent.append("Cell=");
		reqContent.append("&");
		reqContent.append("SendTime=");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("CorpID", username);
		paramMap.put("Pwd", password);
		paramMap.put("Mobile", phoneNum);
		paramMap.put("Content", content);
		paramMap.put("Cell", "");
		paramMap.put("SendTime", "");

		// System.out.println("=======charset:" + getEncoding(content));
		System.out.println("=======reqContent:" + reqContent.toString());

		HttpClientUtils.sendPost(url, paramMap, "gb2312", "application/x-www-form-urlencoded");
		// System.out.println("=============:" + ret);
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	private static void sendGetNew(String urlOrig, String data) throws IOException {
		System.out.println(data);
		URL url = new URL(urlOrig);
		StringBuffer bankXmlBuffer = new StringBuffer();
		// 创建URL连接，提交到银行卡鉴权，获取返回结果
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("User-Agent", "directclient");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		PrintWriter out = new PrintWriter(connection.getOutputStream());
		out.println(data);
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			bankXmlBuffer.append(inputLine);
		}
		in.close();
		System.out.println("XXXXXXXXXX：" + bankXmlBuffer.toString());
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://yzm.mb345.com/ws/LinkWS.asmx/BatchSend";
		String username = "CQLKY077";
		String password = "ss1103@";
		String phoneNum = "15101542114";
		String content = "当前验证码为：987546,工作人员不会向您索取，请勿泄露。";
		// String content = "666666";
		sendMsg(url, username, password, phoneNum, content);

		// System.out.println(getEncoding(content));
	}

	public static String getEncoding(String str) {
		String encode = null;
		// String encode = "GB2312";
		// try {
		// if (str.equals(new String(str.getBytes(encode), encode))) { //
		// 判断是不是GB2312
		// String s = encode;
		// return s; // 是的话，返回“GB2312“，以下代码同理
		// }
		// } catch (Exception exception) {
		// }
		// encode = "ISO-8859-1";
		// try {
		// if (str.equals(new String(str.getBytes(encode), encode))) { //
		// 判断是不是ISO-8859-1
		// String s1 = encode;
		// return s1;
		// }
		// } catch (Exception exception1) {
		// }
		// encode = "UTF-8";
		// try {
		// if (str.equals(new String(str.getBytes(encode), encode))) { //
		// 判断是不是UTF-8
		// String s2 = encode;
		// return s2;
		// }
		// } catch (Exception exception2) {
		// }
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return ""; // 如果都不是，说明输入的内容不属于常见的编码格式。
	}

	public static String httpGet(String url) throws ClientProtocolException, IOException, URISyntaxException {
		HttpClient httpclient = new DefaultHttpClient();
		String result = "";
		try {
			// 连接超时
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 读取超时
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);

			HttpGet hg = new HttpGet(url);
			// 模拟浏览器
			hg.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");
			String charset = "UTF-8";
			hg.setURI(new java.net.URI(url));
			HttpResponse response = httpclient.execute(hg);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				charset = getContentCharSet(entity);
				// 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1
				result = EntityUtils.toString(entity, charset);
			}

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static String getContentCharSet(final HttpEntity entity) throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		return charset;
	}
}
