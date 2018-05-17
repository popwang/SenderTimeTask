package com.webservice.gy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class GyQuartz extends AbstactBaseQuartz{

	@Autowired
	private GyService gyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return gyService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_GY_SYSTEM;
	}
}
