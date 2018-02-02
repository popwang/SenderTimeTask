package com.tcp.xa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component
public class XaQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XaQuartz.class);
	@Autowired
	private XaService xaServer;
	@Override
	public void startTimeTask() {
		log.info("西安数据开始发送...");
		xaServer.handler();
		log.info("西安数据发送完成！");
	}

}
