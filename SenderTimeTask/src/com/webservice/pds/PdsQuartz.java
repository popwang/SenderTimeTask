package com.webservice.pds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pdsQuartz")
public class PdsQuartz {
	public static Log log = LogFactory.getLog(PdsQuartz.class);
	@Autowired
	private PdsService service;
	
	public void startPdsTimeTask(){
		log.info("平顶山宝丰县数据发送开始...");
		service.handler();
		log.info("平顶山宝丰县数据发送完成！");
	}
}
