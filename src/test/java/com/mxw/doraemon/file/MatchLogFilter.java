package com.mxw.doraemon.file;

import java.io.*;

public class MatchLogFilter {

	public static void main(String[] args) {
//		String str = "February 5th 2018, 21:25:55.911	message:2018-02-05 13:25:55.331 INFO 24738 --- [MessageThread_1] c.itranswarp.crypto.match.MatchService : [0205_132555_269_0000100292_ecc5] process order message: {OrderMessage: type=CANCEL_BUY_LIMIT, symbol=BDB_ETH, seqId=972, orderId=100971, userId=100292, refSeqId=957, refOrderId=100956, price=0.0001180100000000} path:/var/log/match.log @timestamp:February 5th 2018, 21:25:55.911 @version:1 host:bw-onli-match-02 _id:AWFmJQCjtaXQSow_rq4- _type:logs _index:match-02 _score: -";
//		System.out.println(str.substring(str.indexOf("seqId="), str.indexOf("seqId=") + 9));
		 readFileByLines("/alandev/match-02.txt");
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) {
		StringBuffer sb = new StringBuffer();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				line++;
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				if (tempString.indexOf("process order message") != -1) {
					sb.append(tempString.substring(tempString.indexOf("seqId="), tempString.indexOf("seqId=") + 9));
					sb.append("\n");
				} else {
					continue;
				}
			}
			egBufferedWriter(sb.toString());
			// System.out.println(sb.toString());
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
	}

	/**
	 * 缓冲字符（BufferedWriter ）是一个字符流类来处理字符数据。不同于字节流（数据转换成字节），可以直接写字符串，数组或字符数据保存到文件。
	 */
	private static void egBufferedWriter(String content) {
		try {
			File file = new File("/alandev/match-02-process.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
