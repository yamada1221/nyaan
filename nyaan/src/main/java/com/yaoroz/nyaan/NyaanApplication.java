package com.yaoroz.nyaan;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.yaoroz.nyaan.common.csv.CSVReader;
import com.yaoroz.nyaan.service.HomeServiceImpl;

@SpringBootApplication
@EnableScheduling
public class NyaanApplication {

	/** ログ */
	private static final Logger log = LoggerFactory.getLogger(HomeServiceImpl.class);

	public static void main(String[] args) {
		CSVReader cSVReader = new CSVReader();
		try {
			cSVReader.read();
		} catch (IOException e) {
			log.error("ログ読み込みエラー", e);
		}
		SpringApplication.run(NyaanApplication.class, args);
	}

}
