package com.alanma.doraemon.utils.appreq;

public class Sign {
	private String signature;
	private String signMethod;

	public Sign(String signature, String signMethod) {
		super();
		this.signature = signature;
		this.signMethod = signMethod;
	}

	public Sign() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	@Override
	public String toString() {
		return "Sign [signature=" + signature + ", signMethod=" + signMethod + "]";
	}

}