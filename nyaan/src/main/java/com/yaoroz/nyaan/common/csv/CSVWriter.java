package com.yaoroz.nyaan.common.csv;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.yaoroz.nyaan.bean.CounterDetails;

public class CSVWriter extends CSVManager {

	CounterDetails counter = CounterDetails.getInstance();

	public void write() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(CounterDetails.class);
		ObjectWriter writer = mapper.writerFor(CounterDetails.class).with(schema);
		writer.writeValue(new File(getCSVFilePath()), counter);
//			// CSVファイルを全行まとめて読み込む場合
//			List<Counter> userList = it.readAll();
//			for (Counter user : userList) {
//
//				// User(id=001, name=Alice, age=18)
//				// User(id=002, name=Bob, age=25)
//				// User(id=003, name=Carol, age=23)
//				System.out.println(user.toString());
//			}
//		}
	}
}
