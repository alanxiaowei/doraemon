package com.alanma.doraemon.utils.readfiles;

import java.io.File;

public class FileNameReader {

	public static void main(String[] args) {
		String path = "I:/影音媒体/视频/求婚";
		File file = new File(path);
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				// System.out.println("文     件：" + tempList[i]);
				System.out.println(tempList[i].getName());
			}
			if (tempList[i].isDirectory()) {
				// System.out.println("文件夹路径：" + tempList[i].getName());
				System.out.println(tempList[i].getName());
			}
		}
	}

}
