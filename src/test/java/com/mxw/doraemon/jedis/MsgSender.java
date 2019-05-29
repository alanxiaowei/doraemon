package com.mxw.doraemon.jedis;

import com.mxw.doraemon.utils.jedis.JedisUtil;

public class MsgSender implements Runnable {

	private int index;

	public MsgSender(int index) {
		super();
		this.index = index;
	}

	@Override
	public void run() {
		JedisUtil.setex("test:mxw:jedis:", 60, index + "");
		System.out.println("==================SEQ:" + index);
	}
}
