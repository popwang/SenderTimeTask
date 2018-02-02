package com.webservice.ayhx;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ayhxQuartz")
public class AyhxQuartz {
	public static Log log = LogFactory.getLog(AyhxQuartz.class);
	@Autowired
	private AyhxService service;
	
	public void startAyhxTimeTask(){
		log.info("�����������ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("�����������ݷ�����ɣ�");
	}
}
