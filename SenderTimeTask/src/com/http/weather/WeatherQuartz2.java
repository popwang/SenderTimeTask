package com.http.weather;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * ��������������ʱ��
 * @author Administrator
 */
@Component
public class WeatherQuartz2 implements QuartzInterface {
	
	public static Log log = LogFactory.getLog(WeatherQuartz2.class);
	@Autowired
	private WeatherService2 weatherService;
	
	@Override
	public void startTimeTask() {
		log.info("ץȡ����Ԥ����Ϣ��ʼ...");
		weatherService.handler();
		log.info("ץȡ����Ԥ����Ϣ��ɣ�");
	}

}
