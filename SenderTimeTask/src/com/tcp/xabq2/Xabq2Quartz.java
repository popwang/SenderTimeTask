package com.tcp.xabq2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.common.QuartzInterface;
public class Xabq2Quartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(Xabq2Quartz.class);

	private Xabq2Service service;
	
	@Override
	public void startTimeTask() {
		log.info("���������2���ݷ��Ϳ�ʼ...");
//		service.run();
		log.info("���������2�������ݷ�����ɣ�");
	}

}
