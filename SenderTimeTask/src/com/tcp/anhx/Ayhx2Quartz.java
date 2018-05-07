package com.tcp.anhx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Ayhx2Quartz extends AbstactBaseQuartz{
	
	@Autowired
	private Ayhx2Service ayhx2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return ayhx2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.AY_HX2_SYSTEM;
	}
	
}
