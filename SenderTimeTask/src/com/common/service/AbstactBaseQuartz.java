package com.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.common.QuartzInterface;
import com.utils.SystemEnum;

public abstract class AbstactBaseQuartz implements QuartzInterface {
	
	public static Log log = LogFactory.getLog(AbstactBaseQuartz.class);

	@Override
	public void startTimeTask() {
		AbstractBaseService service = getAbstractBaseService();
		SystemEnum systemEnum = getSystemEnum();
		log.info(systemEnum.getName()+"���ݿ�ʼ����...");
		service.handler(systemEnum);
		log.info(systemEnum.getName()+"������ɣ�");
	}
	/**
	 * ��ʱ����ʵ�������ע���Ǹ�service��ҵ�����
	 * @return
	 */
	public abstract AbstractBaseService getAbstractBaseService();
	/**
	 * �趨�ӿڵ�ö������
	 * @return
	 */
	public abstract SystemEnum getSystemEnum();
}
