package com.tcp.aynew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class AynewQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private AynewService aynewService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return aynewService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.AY_NEW_SYSTEM;
	}

}
