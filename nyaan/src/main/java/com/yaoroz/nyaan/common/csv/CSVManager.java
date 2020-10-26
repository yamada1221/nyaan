package com.yaoroz.nyaan.common.csv;

import org.springframework.beans.factory.annotation.Value;

public class CSVManager {

	@Value("${com.yaoroz.nyaan.common.csv.csvFilePath}")
	private String csvFilePath;

	public String getCsvFilePath() {
		return csvFilePath;
	}

	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

}
