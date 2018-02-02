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
		log.info("֣�ݽ�ί���ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("֣�ݽ�ί�������ݷ�����ɣ�");
	}
}
