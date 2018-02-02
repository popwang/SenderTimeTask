package com.http.weather;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * 阿里云天气服务定时器
 * @author Administrator
 */
@Component
public class WeatherQuartz2 implements QuartzInterface {
	
	public static Log log = LogFactory.getLog(WeatherQuartz2.class);
	@Autowired
	private WeatherService2 weatherService;
	
	@Override
	public void startTimeTask() {
		log.info("抓取天气预报信息开始...");
		weatherService.handler();
		log.info("抓取天气预报信息完成！");
	}

}
