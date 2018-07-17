package com.webservice.yc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YcQuartz {
	public static Log log = LogFactory.getLog(YcQuartz.class);
	@Autowired
	private YcService ycService;
	
	public void startTimeTask() {
		log.info("�����������ݷ��Ϳ�ʼ...");
		ycService.hanlder();
		log.info("�����������ݷ�����ɣ�");
	}
}
