package com.webservice.weather;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.QuartzInterface;
/**
 * 由于此服务信息不全且不稳定，此方法已废弃，被WeatherQuartz2替代
 * 抓取天气预报信息定时任务，每天的7点，11点，15点，19点抓取天气预报信息
 * @author pactera
 */

public class WeatherQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(WeatherQuartz.class);
	@Autowired
	private WeatherService weatherService;
	
	@Override
	public void startTimeTask() {
		log.info("抓取天气预报信息开始...");
		weatherService.handler();
		log.info("抓取天气预报信息完成！");
	}

}
