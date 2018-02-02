package com.tcp.xanew;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component
public class XanewQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XanewQuartz.class);
	@Autowired
	private XanewService service;
	
	@Override
	public void startTimeTask() {
		log.info("�������������ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("�����������������ݷ�����ɣ�");
	}
	
	

}
