package com.tcp.xabq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
@Component
public class XabqQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XabqQuartz.class);
	
	@Override
	public void startTimeTask() {
		log.info("西安灞桥区数据发送开始...");
//		service.run();
		log.info("西安灞桥区本轮数据发送完成！");
	}

}
