package com.yaoroz.nyaan.common.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.yaoroz.nyaan.bean.CounterDetails;

@Component
public class CSVReader extends CSVManager {

	/** ログ */
	private static final Logger log = LoggerFactory.getLogger(CSVReader.class);

	public void read() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(CounterDetails.class);
		CounterDetails counter = CounterDetails.getInstance();
		Path path = Paths.get(getCsvFilePath());
		try (BufferedReader br = Files.newBufferedReader(path)) {

			MappingIterator<CounterDetails> it = mapper.readerFor(CounterDetails.class).with(schema).readValues(br);

			while (it.hasNextValue()) {
				CounterDetails counterCsv = it.nextValue();
				counter.setCount(counterCsv.getCount());
				counter.setView(counterCsv.getView());
				counter.setJsonRequest(counterCsv.getJsonRequest());
				counter.setGraphRequest(counterCsv.addGraphRequest());

				log.debug("count={},view={},jsonRequest={},graphRequest={}", counter.getCount(), counter.getView(),
						counter.getJsonRequest(), counter.getGraphRequest());
			}
		}
	}
}
