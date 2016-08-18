package com.alanma.doraemon.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestArrayString {

    public static void main(String[] args) {
        String str = "测试字符转换 hello word";
        try {
            String strGBK = URLEncoder.encode(str, "GBK");
            System.out.println("[1]:" + strGBK);
            System.out.println("[2]:" + strGBK.getBytes());
            System.out.println("[3]:" + new String(strGBK.getBytes(), "UTF-8"));
            String strUTF8 = URLDecoder.decode(str, "UTF-8");
            System.out.println("[4]:" + strUTF8);
            byte[] bytes = strUTF8.getBytes();
            System.out.println("[5]:" + strUTF8.getBytes());
            System.out.println("[6]:" + new String(strUTF8.getBytes()));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
