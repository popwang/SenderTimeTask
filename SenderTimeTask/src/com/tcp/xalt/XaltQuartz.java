package com.tcp.xalt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component
public class XaltQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(XaltQuartz.class);
	@Autowired
	private XaltService service;
	
	@Override
	public void startTimeTask() {
		log.info("西安临潼区数据发送开始...");
		service.handler();
		log.info("西安临潼区本轮数据发送完成！");
	}

}
