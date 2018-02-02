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
		log.info("ɽ���������ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("ɽ�������������ݷ�����ɣ�");
	}
}
