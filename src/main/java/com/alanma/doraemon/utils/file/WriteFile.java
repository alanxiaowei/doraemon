package com.alanma.doraemon.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

	/**
	 * 缓冲字符（BufferedWriter
	 * ）是一个字符流类来处理字符数据。不同于字节流（数据转换成字节），可以直接写字符串，数组或字符数据保存到文件。
	 */
	private static void egBufferedWriter() {
		try {
			String content = "This is the content to write into file";
			File file = new File("D:/testfile.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件输出流是一种用于处理原始二进制数据的字节流类。为了将数据写入到文件中，必须将数据转换为字节，并保存到文件。请参阅下面的完整的例子。
	 */
	private static void egFileOutputStream(String content) {
		FileOutputStream fop = null;
		File file;
		try {
			file = new File("d:/newfile.txt");
			fop = new FileOutputStream(file,true);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("[write file success]");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		egBufferedWriter();
	}

}
