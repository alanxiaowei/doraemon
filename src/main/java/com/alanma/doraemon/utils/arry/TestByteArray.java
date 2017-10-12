package com.alanma.doraemon.utils.arry;

public class TestByteArray {

	public static void main(String[] args) {
		byte[] sendMsg=new String("abcdefg").getBytes();
		byte[] sendMsgFe=new byte[sendMsg.length];
		System.arraycopy(sendMsg, 0, sendMsgFe, 0, sendMsg.length);
		System.out.println("==============:"+new String(sendMsgFe));
	}

}
