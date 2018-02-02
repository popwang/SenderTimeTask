package com.webservice.kf2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * 开封2对接定时器
 * @author pactera
 */
@Component
public class Kf2Quartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(Kf2Quartz.class);
	@Autowired
	private Kf2Service service;
	
	@Override
	public void startTimeTask() {
		log.info("开封2数据发送开始...");
		service.handler();
		log.info("开封2数据发送完成！");
	}

}
