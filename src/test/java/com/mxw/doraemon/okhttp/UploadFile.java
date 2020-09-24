package com.mxw.doraemon.okhttp;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @program: doraemon
 * @description: 上传文件
 * @author: AlanMa
 * @create: 2020-03-12 17:10
 */
public class UploadFile {

	String token = null;

	ResponseBody uploadFile(String url, String filePath, String fileName) throws Exception {
		OkHttpClient client = new OkHttpClient();
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath))).build();

		Request request = new Request.Builder().header("TOKEN", token).addHeader("Source","Web").url(url).post(requestBody).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		return response.body();
	}
}
