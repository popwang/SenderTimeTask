package com.udp.hb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component("hbQuartz")
public class HbQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(HbQuartz.class);
	@Autowired
	private HbServer hbServer;
	
	@Override
	public void startTimeTask() {
		log.info("�ӱ�ʯ��ׯ���ݷ��Ϳ�ʼ...");
		hbServer.handler();
		log.info("�ӱ�ʯ��ׯ������ɣ�");
	}

}
