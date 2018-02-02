package com.tcp.xadx;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * ������������ʱ����
 * @author Administrator
 */
@Component
public class XadxQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XadxQuartz.class);
	@Autowired
	private XadxService service;
	
	@Override
	public void startTimeTask() {
		log.info("�������������ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("�����������������ݷ�����ɣ�");
	}

}
