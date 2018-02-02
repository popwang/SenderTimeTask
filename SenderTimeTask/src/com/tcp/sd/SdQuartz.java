package com.tcp.sd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sdQuartz")
public class SdQuartz {
	
	public static Log log = LogFactory.getLog(SdQuartz.class);
	@Autowired
	private SdService service;
	
	public void startSdjnTimeTask(){
		log.info("山东济宁数据发送开始...");
		service.handler();
		log.info("山东济宁本轮数据发送完成！");
	}
}
