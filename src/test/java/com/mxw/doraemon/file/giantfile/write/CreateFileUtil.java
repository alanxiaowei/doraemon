package com.mxw.doraemon.file.giantfile.write;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CreateFileUtil {

	public static void main(String[] args) {
		String[] strs = new String[2];
		strs[0] = "testhhahah";
		strs[1] = "1111122222";
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(strs);
		String filePath = "D:\\";
		String fileName = "testfile";
		System.out.println(createTxtFile(list, filePath, fileName));
	}

	/**
	 * 生成.TXT格式文件,行数几乎无上限
	 */
	public static boolean createTxtFile(List<Object[]> rows, String filePath, String fileName) {
		// 标记文件生成是否成功
		boolean flag = true;

		try {
			// 含文件名的全路径
			String fullPath = filePath + File.separator + fileName + ".txt";

			File file = new File(fullPath);
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			file = new File(fullPath);
			file.createNewFile();

			// 格式化浮点数据
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setMaximumFractionDigits(10); // 设置最大小数位为10

			// 格式化日期数据
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			// 遍历输出每行
			// 如果需要追加写入
			//FileWriter fr=new FileWriter(fileWrite, true);
			//pfp = new PrintWriter(fr);
			PrintWriter pfp = new PrintWriter(file, "UTF-8"); // 设置输出文件的编码为utf-8
			for (Object[] rowData : rows) {
				StringBuffer thisLine = new StringBuffer("");
				for (int i = 0; i < rowData.length; i++) {
					Object obj = rowData[i]; // 当前字段

					// 格式化数据
					String field = "";
					if (null != obj) {
						if (obj.getClass() == String.class) { // 如果是字符串
							field = (String) obj;
						} else if (obj.getClass() == Double.class || obj.getClass() == Float.class) { // 如果是浮点型
							field = formatter.format(obj); // 格式化浮点数,使浮点数不以科学计数法输出
						} else if (obj.getClass() == Integer.class || obj.getClass() == Long.class
								|| obj.getClass() == Short.class || obj.getClass() == Byte.class) { // 如果是整形
							field += obj;
						} else if (obj.getClass() == Date.class) { // 如果是日期类型
							field = sdf.format(obj);
						}
					} else {
						field = " "; // null时给一个空格占位
					}

					// 拼接所有字段为一行数据，用tab键分隔
					if (i < rowData.length - 1) { // 不是最后一个元素
						thisLine.append(field).append("\t");
					} else { // 是最后一个元素
						thisLine.append(field);
					}
				}
				pfp.print(thisLine.toString() + "\n");
			}
			pfp.close();

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 生成.csv格式文件,行数几乎无上限
	 */
	public static boolean createCsvFile(List<Object[]> rows, String filePath, String fileName) {
		// 标记文件生成是否成功
		boolean flag = true;

		// 文件输出流
		BufferedWriter fileOutputStream = null;

		try {
			// 含文件名的全路径
			String fullPath = filePath + File.separator + fileName + ".csv";

			File file = new File(fullPath);
			if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
				file.getParentFile().mkdirs();
			}
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			file = new File(fullPath);
			file.createNewFile();

			// 格式化浮点数据
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setMaximumFractionDigits(10); // 设置最大小数位为10

			// 格式化日期数据
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			// 实例化文件输出流
			fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"), 1024);

			// 遍历输出每行
			Iterator<Object[]> ite = rows.iterator();
			while (ite.hasNext()) {
				Object[] rowData = (Object[]) ite.next();
				for (int i = 0; i < rowData.length; i++) {
					Object obj = rowData[i]; // 当前字段
					// 格式化数据
					String field = "";
					if (null != obj) {
						if (obj.getClass() == String.class) { // 如果是字符串
							field = (String) obj;
						} else if (obj.getClass() == Double.class || obj.getClass() == Float.class) { // 如果是浮点型
							field = formatter.format(obj); // 格式化浮点数,使浮点数不以科学计数法输出
						} else if (obj.getClass() == Integer.class || obj.getClass() == Long.class
								|| obj.getClass() == Short.class || obj.getClass() == Byte.class) { // 如果是整形
							field += obj;
						} else if (obj.getClass() == Date.class) { // 如果是日期类型
							field = sdf.format(obj);
						}
					} else {
						field = " "; // null时给一个空格占位
					}
					// 拼接所有字段为一行数据
					if (i < rowData.length - 1) { // 不是最后一个元素
						fileOutputStream.write("\"" + field + "\"" + ",");
					} else { // 是最后一个元素
						fileOutputStream.write("\"" + field + "\"");
					}
				}
				// 创建一个新行
				if (ite.hasNext()) {
					fileOutputStream.newLine();
				}
			}
			fileOutputStream.flush();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}