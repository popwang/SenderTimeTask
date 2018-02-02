package com.http.aysm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("aysmQuartz")
public class AysmQuartz {
	public static Log log = LogFactory.getLog(AysmQuartz.class);
	@Autowired
	private AysmService service;
	
	public void startAysmTimeTask(){
		log.info("��������Ͽ���ݷ��Ϳ�ʼ...");
		service.service();
		log.info("��������Ͽ�������ݷ�����ɣ�");
	}
}
