package com.yaoroz.nyaan.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Counter {

	private static Counter counter = new Counter();

	public static Counter getInstance() {
		return counter;
	}

	private long count = 0;
	@JsonIgnore
	private long view = 0;
	@JsonIgnore
	private long jsonRequest = 0;
	@JsonIgnore
	private long graphRequest = 0;

	public long getCount() {
		return count;
	}

	public long getView() {
		return view;
	}

	public long getJsonRequest() {
		return jsonRequest;
	}

	public long getGraphRequest() {
		return graphRequest;
	}

	public long addCount() {
		return count++;
	}

	public long addView() {
		return view++;
	}

	public long addJsonRequest() {
		return jsonRequest++;
	}

	public long addGraphRequest() {
		return graphRequest++;
	}
}
