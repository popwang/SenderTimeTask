package com.tcp.ly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class LyQuartz extends AbstactBaseQuartz{
	@Autowired
	private LyService service;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return service;
	}
	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HA_LY_SYSTEM;
	}
}
