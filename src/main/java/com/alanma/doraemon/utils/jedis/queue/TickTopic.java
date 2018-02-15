package com.alanma.doraemon.utils.jedis.queue;

public class TickTopic {
	public  String symbol;
	public  float price;
	public  float amount;
	public  long createdAt;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "TickTopic [symbol=" + symbol + ", price=" + price + ", amount=" + amount + ", createdAt=" + createdAt
				+ "]";
	}
	
	
}
