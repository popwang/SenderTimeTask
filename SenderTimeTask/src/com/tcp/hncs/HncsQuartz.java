package com.tcp.hncs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
@Component
public class HncsQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(HncsQuartz.class);
	@Autowired
	private HncsService service;
	
	@Override
	public void startTimeTask() {
		log.info("湖南长沙数据发送开始...");
		service.handler();
		log.info("湖南长沙本轮数据发送完成！");
	}

}
