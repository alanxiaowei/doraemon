package com.mxw.doraemon.sql;

import java.io.*;

/**
 * @program: doraemon
 * @description: SQL驼峰转换
 * @author: AlanMa
 * @create: 2021-09-02 18:34
 */
public class Parsehump {

    public static String getInputParams(String fileName) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);
        Reader reader = null;
        try {
            //            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    // System.out.print((char) tempchar);
                    sb.append((char) tempchar);
                }
            }
            //            System.out.println(sb.toString());
            reader.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/Users/maxinwei/baidu/tmp/a.sql";
        getInputParams(path);
    }
}
