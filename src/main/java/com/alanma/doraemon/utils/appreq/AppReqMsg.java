package com.alanma.doraemon.utils.appreq;

public class AppReqMsg {
	private String sign;
	private String encry;
	private String reqHead;
	private String data;

	public AppReqMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppReqMsg(String sign, String encry, String reqHead, String data) {
		super();
		this.sign = sign;
		this.encry = encry;
		this.reqHead = reqHead;
		this.data = data;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEncry() {
		return encry;
	}

	public void setEncry(String encry) {
		this.encry = encry;
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
		return "MsgInfo [sign=" + sign + ", encry=" + encry + ", reqHead=" + reqHead + ", data=" + data + "]";
	}

}
