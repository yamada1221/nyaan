package com.yaoroz.nyaan.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yaoroz.nyaan.common.csv.CSVReader;
import com.yaoroz.nyaan.common.csv.CSVWriter;

@Configuration
public class NyaanConfiguration {

	/** ログ */
	private static final Logger log = LoggerFactory.getLogger(NyaanConfiguration.class);

	/** CSV読み込み */
	@Autowired
	private CSVReader cSVReader;

	/** CSV書き込み */
	@Autowired
	private CSVWriter cSVWriter;

	@Bean
	CSVReader cSVReader() {
		try {
			log.info("CsvFilePath={}", cSVReader.getCsvFilePath());
			cSVReader.read();
		} catch (IOException e) {
			log.error("ログ読み込みエラー", e);
		}
		return cSVReader;
	}

	@Bean
	CSVWriter cSVWriter() {
		return cSVWriter;
	}

}
