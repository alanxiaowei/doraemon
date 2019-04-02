package com.alanma.doraemon.utils.redisson;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserVolume implements Serializable {

	private static final long serialVersionUID = 5213233326115592278L;

	BigDecimal volume;

	Long userId;

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserVolume [volume=" + volume + ", userId=" + userId + "]";
	}

}
