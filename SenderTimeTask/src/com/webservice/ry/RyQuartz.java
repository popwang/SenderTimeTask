package com.webservice.ry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ryQuartz")
public class RyQuartz{
	public static Log log = LogFactory.getLog(RyQuartz.class);
	@Autowired
	private RyService ryService;
	
	public void startTimeTask() {
		log.info("�����������ݷ��Ϳ�ʼ...");
		ryService.hanlder();
		log.info("�����������ݷ�����ɣ�");
	}

}
