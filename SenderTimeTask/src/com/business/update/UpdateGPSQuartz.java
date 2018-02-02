package com.business.update;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

/**
 * ��ʱ��data2�ֵľ�γ�ȸ��µ�info����ת��Ϊ�ٶ�����
 * @author pactera
 */
@Component
public class UpdateGPSQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(UpdateGPSQuartz.class);
	@Autowired
	private UpdateEquipmentInfoService updateService;
	@Override
	public void startTimeTask() {
		log.info("��ʼ�������µľ�γ�ȵ��豸��Ϣ��...");
		updateService.handler2();
		log.info("�������µľ�γ�ȵ��豸��Ϣ����ɣ�");
	}

}
