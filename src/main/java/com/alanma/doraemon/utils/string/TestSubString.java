package com.alanma.doraemon.utils.string;

public class TestSubString {

    public static void main(String[] args) {
        String origMsg = "00001530<?xml version=\"1.0\" encoding=\"gb2312\"?>";
        String msg = origMsg.substring(origMsg.indexOf("<?"), origMsg.length());
        System.out.println(msg);
    }
}
