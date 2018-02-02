package com.webservice.weather;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.QuartzInterface;
/**
 * ���ڴ˷�����Ϣ��ȫ�Ҳ��ȶ����˷����ѷ�������WeatherQuartz2���
 * ץȡ����Ԥ����Ϣ��ʱ����ÿ���7�㣬11�㣬15�㣬19��ץȡ����Ԥ����Ϣ
 * @author pactera
 */

public class WeatherQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(WeatherQuartz.class);
	@Autowired
	private WeatherService weatherService;
	
	@Override
	public void startTimeTask() {
		log.info("ץȡ����Ԥ����Ϣ��ʼ...");
		weatherService.handler();
		log.info("ץȡ����Ԥ����Ϣ��ɣ�");
	}

}
