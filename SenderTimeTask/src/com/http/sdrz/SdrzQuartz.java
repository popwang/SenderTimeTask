package com.http.sdrz;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
import com.common.ServerInterface;

@Component
public class SdrzQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(SdrzQuartz.class);
	@Resource(name="sdrzServer")
	private ServerInterface server;
	
	@Override
	public void startTimeTask() {
		log.info("ɽ���������ݷ��Ϳ�ʼ...");
		server.handler();
		log.info("ɽ���������ݷ�����ɣ�");
	}

}
