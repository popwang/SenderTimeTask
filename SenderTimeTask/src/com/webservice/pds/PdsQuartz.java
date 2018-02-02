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
		log.info("ƽ��ɽ���������ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("ƽ��ɽ���������ݷ�����ɣ�");
	}
}
