package com.tcp.xanew;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component
public class XanewQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XanewQuartz.class);
	@Autowired
	private XanewService service;
	
	@Override
	public void startTimeTask() {
		log.info("西安高新区数据发送开始...");
		service.handler();
		log.info("西安高新区本轮数据发送完成！");
	}
	
	

}
