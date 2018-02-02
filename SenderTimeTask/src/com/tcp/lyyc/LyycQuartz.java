package com.tcp.lyyc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
@Component
public class LyycQuartz implements QuartzInterface {	
	public static Log log = LogFactory.getLog(LyycQuartz.class);
	@Autowired
	private LyycService service;
	@Override
	public void startTimeTask() {
		log.info("洛阳伊川数据发送开始...");
		service.handler();
		log.info("洛阳伊川本轮数据发送完成！");
	}
}
