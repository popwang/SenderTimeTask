package com.tcp.xxcy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class XxcyQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private XxcyService xxcyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xxcyService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XX_CY_SYSTEM;
	}
	
}
