package com.yaoroz.nyaan.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaoroz.nyaan.bean.CounterDetails;

@Service
public class HomeServiceImpl implements HomeService {

	/** ログ */
	private static final Logger log = LoggerFactory.getLogger(HomeServiceImpl.class);
	/** カウンタ */
	private CounterDetails counter = CounterDetails.getInstance();
	/** ObjectMapper */
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public void index(HttpServletRequest request, HttpServletResponse response) {
		counter.addView();
	}

	@Override
	public void json(HttpServletRequest request, HttpServletResponse response) {
		if (RequestMethod.GET.name().equals(request.getMethod())) {
			counter.addJsonRequest();
		} else {
			counter.addCount();
		}
		PrintWriter out = createPrintWriter(response);
		out.println(createCountJson());
	}

	@Override
	public void graph(HttpServletRequest request, HttpServletResponse response) {
		counter.addGraphRequest();
		PrintWriter out = createPrintWriter(response);
		out.println(createGraphJson(out));
	}

	/**
	 * PrintWriterを生成する。
	 * 
	 * @return PrintWriter
	 */
	protected PrintWriter createPrintWriter(HttpServletResponse response) {
		// jsonで値を返す
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("PrintWriter生成エラー", e);
		}
		return out;
	}

	/**
	 * カウンタのjsonを生成する。
	 * 
	 * @return json文字列
	 */
	protected String createCountJson() {
		String json = null;
		try {
			json = mapper.writeValueAsString(counter);
		} catch (JsonProcessingException e) {
			log.error("グラフjson生成エラー", e);
		}
		log.debug(json);
		return json;
	}

	/**
	 * グラフのjsonを生成する。
	 * 
	 * @return グラフのjson文字列
	 */
	protected String createGraphJson(PrintWriter out) {
		JsonFactory jf = new JsonFactory();
		JsonGenerator jg = null;
		String json = null;
		try {
			long[] countArray = counter.getCountArray();
			jg = jf.createGenerator(out);
			jg.writeStartObject();
			jg.writeFieldName("countArray");
			jg.writeArray(countArray, 0, countArray.length);
			jg.writeEndObject();
			jg.flush();
			jg.close();
			json = jg.toString();
		} catch (IOException e) {
			log.error("グラフjson生成エラー", e);
		}
		return json;
	}

}
