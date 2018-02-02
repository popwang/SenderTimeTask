package com.tcp.traffic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("trafficQuartz")
public class TrafficQuartz {
	public static Log log = LogFactory.getLog(TrafficQuartz.class);
	@Autowired
	private TrafficService service;
	
	public void startZztraTimeTask(){
		log.info("郑州交委数据发送开始...");
		service.handler();
		log.info("郑州交委本轮数据发送完成！");
	}
}
