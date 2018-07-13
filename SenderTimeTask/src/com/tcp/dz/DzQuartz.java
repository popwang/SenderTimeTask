package com.tcp.dz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class DzQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private DzService dzService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return dzService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HA_DZ_SYSTEM;
	}
	
}
