package com.mxw.doraemon.redisson;

import java.math.BigDecimal;


public class Bar  {

	public String symbol;

	public BarType type;

	public long startTime;

	public BigDecimal openPrice;

	public BigDecimal highPrice;

	public BigDecimal lowPrice;

	public BigDecimal closePrice;

	public BigDecimal amount;

	public void add(BigDecimal price, BigDecimal amount) {
		this.closePrice = price;
		this.highPrice = this.highPrice.max(price);
		this.lowPrice = this.lowPrice.min(price);
		this.amount = this.amount.add(amount);
	}

	public void merge(Bar stick) {
		this.closePrice = stick.closePrice;
		this.highPrice = this.highPrice.max(stick.highPrice);
		this.lowPrice = this.lowPrice.min(stick.lowPrice);
		this.amount = this.amount.add(stick.amount);
	}

	public Number[] getBarData() {
		return new Number[] { this.startTime, this.openPrice, this.highPrice, this.lowPrice, this.closePrice,
				this.amount };
	}

	@Override
	public String toString() {
		return String.format("{Bar: symbol=%s, type=%s, startTime=%s, O=%s, H=%s, L=%s, C=%s, amount=%s}", symbol, type,
				startTime, openPrice, highPrice, lowPrice, closePrice, amount);
	}
}
