package com.yaoroz.nyaan.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaoroz.nyaan.bean.Counter;

@Service
public class HomeServiceImpl implements HomeService {

	/** カウンタ */
	private Counter counter = Counter.getInstance();
	/** 出力 */
	private PrintWriter out = null;
	/** 1時間分のカウント数 */
	private long[] countArray = new long[60];
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
		// jsonで値を返す
		response.setContentType("application/json");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// 何もしない
		}
		out.println(createCountJson());
	}

	@Override
	public void graph(HttpServletRequest request, HttpServletResponse response) {
		counter.addGraphRequest();
		oneMinExecute();
		// jsonで値を返す
		response.setContentType("application/json");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// 何もしない
		}
		out.println(createGraphJson());
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
			// 何もしない
		}
		System.out.println(json);
		return json;
	}

	/**
	 * グラフのjsonを生成する。
	 * 
	 * @return グラフのjson文字列
	 */
	protected String createGraphJson() {
		JsonFactory jf = new JsonFactory();
		JsonGenerator jg = null;
		try {
			jg = jf.createGenerator(out);
			jg.writeStartObject();
			jg.writeFieldName("countArray");
			jg.writeArray(countArray, 0, countArray.length);
			jg.writeEndObject();
			jg.flush();
			jg.close();
		} catch (IOException e) {
			// 何もしない
		}
		return jg.toString();
	}

	/**
	 * 1分間隔でデータ更新。
	 */
	private void oneMinExecute() {
		// 1分間隔の処理
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				// 現在のデータ
				countArray[countArray.length - 1] = counter.getCount();
				for (int i = 0; i < countArray.length; i++) {
					// n分前のデータ
					countArray[i] = countArray[i + 1];
				}
				// TODO:ログ出力(CSV?)
			}
		}, 0, 60, TimeUnit.SECONDS);
	}
}
