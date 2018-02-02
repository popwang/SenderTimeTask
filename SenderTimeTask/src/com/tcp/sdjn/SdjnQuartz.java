package com.tcp.sdjn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component
public class SdjnQuartz implements QuartzInterface {
	
	public static Log log = LogFactory.getLog(SdjnQuartz.class);
	@Autowired
	private SdjnService sdjnServer;
	@Override
	public void startTimeTask() {
		log.info("ɽ���������ݿ�ʼ����...");
		sdjnServer.handler();
		log.info("ɽ�����Ϸ�����ɣ�");
	}

}
