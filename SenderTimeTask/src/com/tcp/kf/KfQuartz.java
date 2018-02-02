package com.tcp.kf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component("kfQuartz")
public class KfQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(KfQuartz.class);
	
	@Autowired
	private KfServer kfServer;
	
	@Override
	public void startTimeTask() {
		log.info("�������ݷ��Ϳ�ʼ...");
		kfServer.handler();
		log.info("�������ݷ�����ɣ�");
	}

}
