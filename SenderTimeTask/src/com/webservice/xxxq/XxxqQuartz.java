package com.webservice.xxxq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
/**
 * �������������ԽӶ�ʱ��
 * @author 27438
 *
 */
@Component
public class XxxqQuartz extends AbstactBaseQuartz{

	@Autowired
	private XxxqService xxxqService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xxxqService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SX_XX_SYSTEM;
	}
}