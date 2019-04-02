package com.alanma.doraemon.utils.okhttp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProxyPool {

	ProxySelector ps;

	public void init() {
		ps = new ProxySelector() {

			@Override
			public List<Proxy> select(URI uri) {
				// 默认任何URI都返回一个代理
				List<Proxy> list = new ArrayList<Proxy>();
				// TODO 添加代理IP
				// ips.forEach(ipElement -> {
				// list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipElement.ip,
				// ipElement.port)));
				// });

				return list;
			}

			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				// 简单地打印信息
				ioe.printStackTrace();
				System.out.println("无法连接到代理！" + uri.toString() + " " + sa.toString());
			}

		};
	}

	public void businessTest() {

		OkHttpClient client = new OkHttpClient.Builder().proxySelector(ps).build();
		// OkHttpClient client = new OkHttpClient.Builder()
		// .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("213.174.6.67",
		// 39563))).build();

		String url = "https://api.binance.com/api/v3/ticker/price";
		Request request = new Request.Builder().get().url(url)
				.addHeader("content-type", "application/json;charset:utf-8").build();
		Response response = null;
		try {
			client.newCall(request).enqueue(new Callback() {

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					System.out.println("============:" + response.toString());
				}

				@Override
				public void onFailure(Call call, IOException e) {
					e.printStackTrace();

				}
			});
			// String prices = response.body().string();
			// System.out.println("============:" + prices);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

}
