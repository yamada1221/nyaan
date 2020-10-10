package com.yaoroz.nyaan.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CounterDetails extends Counter {

	private static CounterDetails counter = new CounterDetails();

	public static CounterDetails getInstance() {
		return counter;
	}

	private long view = 0;
	private long jsonRequest = 0;
	private long graphRequest = 0;
	@JsonIgnore
	/** 1時間分のカウント数 */
	private long[] countArray = new long[60];

	public long getView() {
		return view;
	}

	public long getJsonRequest() {
		return jsonRequest;
	}

	public long getGraphRequest() {
		return graphRequest;
	}

	public long[] getCountArray() {
		return countArray;
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

	public void setView(long view) {
		this.view = view;
	}

	public void setJsonRequest(long jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	public void setGraphRequest(long graphRequest) {
		this.graphRequest = graphRequest;
	}

	public void setCountArray(long[] countArray) {
		this.countArray = countArray;
	}
}
