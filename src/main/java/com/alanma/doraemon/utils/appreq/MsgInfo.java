package com.alanma.doraemon.utils.appreq;

public class MsgInfo {
	private Sign sign;
	private String encryKey;
	private String reqHead;
	private String data;

	public MsgInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MsgInfo(Sign sign, String encryKey, String reqHead, String data) {
		super();
		this.sign = sign;
		this.encryKey = encryKey;
		this.reqHead = reqHead;
		this.data = data;
	}

	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public String getEncryKey() {
		return encryKey;
	}

	public void setEncryKey(String encryKey) {
		this.encryKey = encryKey;
	}

	public String getReqHead() {
		return reqHead;
	}

	public void setReqHead(String reqHead) {
		this.reqHead = reqHead;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	

	@Override
	public String toString() {
		return "MsgInfo [sign=" + sign + ", encryKey=" + encryKey + ", reqHead=" + reqHead + ", data=" + data + "]";
	}

}
