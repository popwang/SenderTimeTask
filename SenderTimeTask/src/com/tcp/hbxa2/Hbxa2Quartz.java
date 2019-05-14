package com.tcp.hbxa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Hbxa2Quartz extends AbstactBaseQuartz{
	@Autowired
	private Hbxa2Service hbxa2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return hbxa2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HB_XA2_SYSTEM;
	}
	
}
