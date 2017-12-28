package com.alanma.doraemon.utils.excel.bdb;

public class PurchaseInfoVO {
	/**
	 * 序号
	 */
	private String number;
	/**
	 * 您的姓名/NAME
	 */
	private String name;
	/**
	 * 您的微信号码/WECHAT
	 */
	private String wechat;
	/**
	 * 您的手机号码/CELLPHONE
	 */
	private String cellphone;
	/**
	 * 您的邮箱/MAIL
	 */
	private String mail;
	/**
	 * 请选择参与的币种/TOKEN YOU CHOOSE
	 */
	private String currency;
	/**
	 * 您所投币种数量/AMOUNT 您的token转出地址/YOUR WITHDRAWAL ADDRESS 参与日期/DATE 转币成功截图/A
	 * SCREEN SHOT OF SUCCESSFUL ASSET TRANSFER 获得BDB数量
	 */
	private String amount;
	/**
	 * 您的token转出地址/YOUR WITHDRAWAL ADDRESS
	 */
	private String rechargeAddr;

	/**
	 * 参与日期/DATE
	 */
	private String date;

	/**
	 * 转币成功截图/A SCREEN SHOT OF SUCCESSFUL ASSET TRANSFER
	 */
	private String screenshort;

	/**
	 * 获得BDB数量
	 */
	private String quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRechargeAddr() {
		return rechargeAddr;
	}

	public void setRechargeAddr(String rechargeAddr) {
		this.rechargeAddr = rechargeAddr;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScreenshort() {
		return screenshort;
	}

	public void setScreenshort(String screenshort) {
		this.screenshort = screenshort;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PurchaseInfoVO [number=" + number + ", name=" + name + ", wechat=" + wechat + ", cellphone=" + cellphone
				+ ", mail=" + mail + ", currency=" + currency + ", amount=" + amount + ", rechargeAddr=" + rechargeAddr
				+ ", date=" + date + ", screenshort=" + screenshort + ", quantity=" + quantity + "]";
	}

}
