package com.alanma.doraemon.utils.okhttp.proxypool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class ProxyIPTest {
	ProxySelector ps;

	String fileName = "/git/doraemon/src/main/java/com/alanma/doraemon/utils/okhttp/proxypool/ip.txt";

	List<IPObj> copyIps = new ArrayList<IPObj>();
	
	public void init() {
		List<IPObj> ips = readIPs();
		copyIps.addAll(ips);
		ps = new ProxySelector() {

			@Override
			public List<Proxy> select(URI uri) {
				// 默认任何URI都返回一个代理
				List<Proxy> list = new ArrayList<Proxy>();
				ips.forEach(ipElement -> {
					list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipElement.ip, ipElement.port)));
				});

				return list;
			}

			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				// 简单地打印信息
				ioe.printStackTrace();
				System.out.println("无法连接到代理！" + uri.toString() + " " + sa.toString());

				removeFailedIP(sa.toString());
			}

		};
	}

	void removeFailedIP(String ip) {
		String[] ipArr = ip.split(":");
		String ipStr = ipArr[0].substring(1, ipArr[0].length());
		IPObj ipObj = new IPObj(ipStr, Integer.parseInt(ipArr[1]));
		System.out.println("TO MOVE IP IS " + ipObj.toString());
		copyIps.remove(ipObj);
		System.out.println(copyIps.toString());
	}

	public void businessTest(String ip, int port) {
		try {
			// OkHttpClient client = new OkHttpClient.Builder().proxySelector(ps).build();
			OkHttpClient client = new OkHttpClient.Builder()
					.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port))).build();

			String url = "https://api.binance.com/api/v3/ticker/price";
			Request request = new Request.Builder().get().url(url)
					.addHeader("content-type", "application/json;charset:utf-8").build();
			Response response = null;

			client.newCall(request).enqueue(new Callback() {

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					System.out.println("============:" + response.toString());
					copyIps.add(new IPObj(ip,port));
				}

				@Override
				public void onFailure(Call call, IOException e) {
					e.printStackTrace();

				}
			});
			// String prices = response.body().string();
			// System.out.println("============:" + prices);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	List<IPObj> readIPs() {
		List<IPObj> ips = new ArrayList<IPObj>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				String[] ipEle = new String[2];
				ipEle = tempString.split(":");
				String ip = ipEle[0];
				String port = ipEle[1].split("#")[0];
				IPObj ipObj = new IPObj(ip, Integer.parseInt(port));
				System.out.println(ipObj.toString());
				ips.add(ipObj);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return ips;
	}

	class IPObj {
		public String ip;
		public int port;

		public IPObj(String ip, int port) {
			super();
			this.ip = ip;
			this.port = port;
		}

		@Override
		public String toString() {
			return "IPObj [ip=" + ip + ", port=" + port + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			if (obj instanceof IPObj) {
				IPObj serviceBean = (IPObj) obj;
				if (serviceBean.ip.equals(this.ip) && serviceBean.port == this.port) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}

	}

	public static void main(String[] args) {

		ProxyIPTest test = new ProxyIPTest();
		List<IPObj> ips = test.readIPs();

		
		ips.forEach(ipObj -> {
			test.businessTest(ipObj.ip, ipObj.port);
		});

		System.out.println("\n~~~finish connecting~~~\n");
		
		try {
			Thread.sleep(300_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n\n~~~The result is:" + test.copyIps.toString() + "\n\n\n");
	}

}
