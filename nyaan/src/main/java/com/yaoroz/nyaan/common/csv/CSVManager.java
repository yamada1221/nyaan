package com.yaoroz.nyaan.common.csv;

import org.springframework.beans.factory.annotation.Value;

public class CSVManager {

	@Value("${com.yaoroz.nyaan.common.csv.csvFilePath:C:\\Users\\dev\\Desktop\\develop\\test.csv}")
	private String csvFilePath = "C:\\Users\\dev\\Desktop\\develop\\test.csv";

	public String getCSVFilePath() {
		return csvFilePath;
	}
}
