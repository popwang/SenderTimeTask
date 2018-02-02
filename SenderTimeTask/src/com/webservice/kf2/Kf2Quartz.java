package com.webservice.kf2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * ����2�ԽӶ�ʱ��
 * @author pactera
 */
@Component
public class Kf2Quartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(Kf2Quartz.class);
	@Autowired
	private Kf2Service service;
	
	@Override
	public void startTimeTask() {
		log.info("����2���ݷ��Ϳ�ʼ...");
		service.handler();
		log.info("����2���ݷ�����ɣ�");
	}

}
