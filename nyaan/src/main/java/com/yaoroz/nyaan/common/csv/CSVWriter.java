package com.yaoroz.nyaan.common.csv;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.yaoroz.nyaan.bean.CounterDetails;

@Component
public class CSVWriter extends CSVManager {

	/** ログ */
	private static final Logger log = LoggerFactory.getLogger(CSVWriter.class);
	/** カウンタ */
	private CounterDetails counter = CounterDetails.getInstance();
	/** CsvMapper */
	private CsvMapper mapper = new CsvMapper();

	public void write() throws IOException {
		CsvSchema schema = mapper.schemaFor(CounterDetails.class);
		ObjectWriter writer = mapper.writerFor(CounterDetails.class).with(schema);
		log.debug("CsvFilePath={}", getCsvFilePath());
		writer.writeValue(new File(getCsvFilePath()), counter);
	}
}
