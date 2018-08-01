package com.tcp.lyyc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class LyycQuartz extends AbstactBaseQuartz{
	@Autowired
	private LyycService service;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return service;
	}
	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.LY_YC_SYSTEM;
	}
}
