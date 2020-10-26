package com.yaoroz.nyaan.bean;

public class Counter {

	private long count = 0;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long addCount() {
		return count++;
	}

}
