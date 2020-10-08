package com.yaoroz.nyaan.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yaoroz.nyaan.bean.CounterDetails;

@Component
public class ScheduledTasks {

	/** ログ */
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	/** 更新日時 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	/** カウンタ */
	private CounterDetails counter = CounterDetails.getInstance();

	/**
	 * 1分間隔でデータ更新。
	 */
	@Scheduled(cron = "0 * * * * *", zone = "Asia/Tokyo")
	public void oneMinExecute() {
		log.debug("The time is now {}", dateFormat.format(new Date()));
		long[] countArray = counter.getCountArray();

		// 現在のデータ
		countArray[countArray.length - 1] = counter.getCount();
		for (int i = 0; i < countArray.length - 1; i++) {
			// n分前のデータ
			countArray[i] = countArray[i + 1];
		}
		// データ更新
		counter.setCountArray(countArray);
		// TODO:ログ出力(CSV?)
	}
}
