package com.tcp.hncs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
@Component
public class HncsQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(HncsQuartz.class);
	@Autowired
	private HncsService service;
	
	@Override
	public void startTimeTask() {
		log.info("���ϳ�ɳ���ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("���ϳ�ɳ�������ݷ�����ɣ�");
	}

}
