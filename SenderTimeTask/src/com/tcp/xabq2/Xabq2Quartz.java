package com.tcp.xabq2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.common.QuartzInterface;
public class Xabq2Quartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(Xabq2Quartz.class);

	private Xabq2Service service;
	
	@Override
	public void startTimeTask() {
		log.info("西安灞桥区2数据发送开始...");
//		service.run();
		log.info("西安灞桥区2本轮数据发送完成！");
	}

}
