package com.tcp.zz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("zztbjQuartz")
public class ZztbjQuartz {
	public static Log log = LogFactory.getLog(ZztbjQuartz.class);
	@Autowired
	private ZztbjService service;
	
	public void startZztbjTimeTask(){
		log.info("郑州特比佳数据发送开始...");
		service.handler();
		log.info("郑州特比佳本轮数据发送完成！");
	}
}
