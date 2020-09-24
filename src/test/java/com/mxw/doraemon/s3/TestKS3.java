package com.mxw.doraemon.s3;

import com.ksyun.ks3.dto.GetObjectResult;
import com.ksyun.ks3.dto.Ks3Object;
import com.ksyun.ks3.dto.ObjectMetadata;
import com.ksyun.ks3.dto.ResponseHeaderOverrides;
import com.ksyun.ks3.http.HttpClientConfig;
import com.ksyun.ks3.service.Ks3;
import com.ksyun.ks3.service.Ks3Client;
import com.ksyun.ks3.service.Ks3ClientConfig;
import com.ksyun.ks3.service.request.GetObjectRequest;
import com.ksyun.ks3.service.request.PutObjectRequest;

import java.io.File;

/**
 * @program: doraemon
 * @description: Test KS3
 * @author: AlanMa
 * @create: 2020-02-25 11:30
 */
public class TestKS3 {

	public static void main(String[] args) {
		Ks3 client=initClient();
		upload(client);
		// read(client);
	}

	private static Ks3 initClient() {
		Ks3ClientConfig config = new Ks3ClientConfig();

		/**
		 * 设置服务地址
		 * 中国（北京）| ks3-cn-beijing.ksyun.com
		 * 中国（上海）| ks3-cn-shanghai.ksyun.com
		 * 中国（香港）| ks3-cn-hk-1.ksyun.com
		 * 中国（杭州）| kss.ksyun.com
		 * 中国（广州）| ks3-cn-guangzhou.ksyun.com
		 * 中国（青岛）| ks3-cn-qingdao.ksyun.com
		 * 金融专区（北京）| ks3-jr-beijing.ksyun.com
		 * 金融专区（上海）| ks3-jr-shanghai.ksyun.com
		 * 俄罗斯 | ks3-rus.ksyun.com
		 * 新加坡 | ks3-sgp.ksyun.com
		 * 如果使用自定义域名，设置endpoint为自定义域名，同时设置domainMode为true

		 */

		config.setEndpoint("ks3-cn-beijing.ksyun.com");   //此处以北京region为例

		/**
		 *true：表示以自定义域名访问
		 *false：表示以KS3的外网域名或内网域名访问，默认为false

		 */

		config.setDomainMode(false);

		config.setProtocol(Ks3ClientConfig.PROTOCOL.http);

		/**
		 *true表示以   endpoint/{bucket}/{key}的方式访问
		 *false表示以  {bucket}.endpoint/{key}的方式访问
		 *如果domainMode设置为true，pathStyleAccess可忽略设置

		 */

		config.setPathStyleAccess(false);

		HttpClientConfig hconfig = new HttpClientConfig();

		//在HttpClientConfig中可以设置httpclient的相关属性，比如代理，超时，重试等。

		config.setHttpClientConfig(hconfig);

		Ks3 client = new Ks3Client("AKLTPvaI6yPjRUSfsLOiZwl4_w", "OFmQp9Lctcb8foQcJQY8ixgi9Wf2lk34sOZha3XusqL5Ei4u+jyAP7Sz1RXLns+m6A==", config);
		return client;
	}

	static void upload(Ks3 client) {

		/* 或者：client.setKs3config(config); */

		PutObjectRequest request = new PutObjectRequest("digitalbank-wallet",

				"123456321", new File("/Users/alanma/alandev/a.jpeg"));

		//上传一个公开文件

		//request.setCannedAcl(CannedAccessControlList.PublicRead);

		client.putObject(request);

	}

	static GetObjectResult read(Ks3 client) {
		GetObjectRequest request = new GetObjectRequest("digitalbank-wallet", "123456321");

		//重写返回的header

		ResponseHeaderOverrides overrides = new ResponseHeaderOverrides();

		overrides.setContentType("text/html");

		//.......

		request.setOverrides(overrides);

		//只接受数据的0-10字节。通过控制该项可以实现分块下载

		//request.setRange(0,10);

		GetObjectResult result = client.getObject(request);

		Ks3Object object = result.getObject();

		//获取object的元数据

		ObjectMetadata meta = object.getObjectMetadata();

		//当分块下载时获取文件的实际大小，而非当前小块的大小

		Long length = meta.getInstanceLength();

		//获取object的输入流

		object.getObjectContent();


		return result;
	}
}
