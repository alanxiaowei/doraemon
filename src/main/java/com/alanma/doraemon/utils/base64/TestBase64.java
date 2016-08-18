package com.alanma.doraemon.utils.base64;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class TestBase64 {

    public static void main(String[] args) {
        String str = "测试Base64";
        String encodeStr = Base64.encode(str.getBytes());
        String decodeStr = null;
        try {
            decodeStr = new String(Base64.decode(encodeStr));
        }
        catch (Base64DecodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(encodeStr);
        System.out.println(decodeStr);
    }
}
