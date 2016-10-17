package com.alanma.doraemon.utils.deflate;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.alanma.doraemon.utils.secret.Base64;

public class TestDeflate {
	/**
	 * 
	 * @param inputByte
	 *            待解压缩的字节数组
	 * @return 解压缩后的字节数组
	 * @throws IOException
	 */
	public static byte[] uncompress(byte[] inputByte) throws IOException {
		int len = 0;
		Inflater infl = new Inflater();
		infl.setInput(inputByte);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] outByte = new byte[1024];
		try {
			while (!infl.finished()) {
				// 解压缩并将解压缩后的内容输出到字节输出流bos中
				len = infl.inflate(outByte);
				if (len == 0) {
					break;
				}
				bos.write(outByte, 0, len);
			}
			infl.end();
		} catch (Exception e) {
			//
		} finally {
			bos.close();
		}
		return bos.toByteArray();
	}

	/**
	 * 压缩.
	 * 
	 * @param inputByte
	 *            待压缩的字节数组
	 * @return 压缩后的数据
	 * @throws IOException
	 */
	public static byte[] compress(byte[] inputByte) throws IOException {
		int len = 0;
		Deflater defl = new Deflater();
		defl.setInput(inputByte);
		defl.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] outputByte = new byte[1024];
		try {
			while (!defl.finished()) {
				// 压缩并将压缩后的内容输出到字节输出流bos中
				len = defl.deflate(outputByte);
				bos.write(outputByte, 0, len);
			}
			defl.end();
		} finally {
			bos.close();
		}
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("D:\\testdeflate.txt");
			int len = fis.available();
			byte[] b = new byte[len];
			getSize(new String(b));
			fis.read(b);
			byte[] bd = compress(b);
			getSize(new String(bd));
			// 为了压缩后的内容能够在网络上传输，一般采用Base64编码
			String encodestr = Base64.encode(bd);
			byte[] bi = uncompress(Base64.decode(encodestr));
			FileOutputStream fos = new FileOutputStream("D:\\testinflate.txt");
			fos.write(bi);
			fos.flush();
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getSize(String content) {
		String size;
		long fileS = content.getBytes().length;
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileS < 1024) {
			size = df.format((double) fileS) + "BT";
		} else if (fileS < 1048576) {
			size = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			size = df.format((double) fileS / 1048576) + "MB";
		} else {
			size = df.format((double) fileS / 1073741824) + "GB";
		}
		System.out.println("size:" + size);
		return size;
	}

}
