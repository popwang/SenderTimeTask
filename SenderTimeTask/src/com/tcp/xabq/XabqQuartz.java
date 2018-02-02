package com.tcp.xabq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
@Component
public class XabqQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XabqQuartz.class);
	@Autowired
	private XabqService service;
	
	@Override
	public void startTimeTask() {
		log.info("������������ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("����������������ݷ�����ɣ�");
	}

}
