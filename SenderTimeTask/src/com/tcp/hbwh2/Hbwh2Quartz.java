package com.tcp.hbwh2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Hbwh2Quartz  extends AbstactBaseQuartz{
	
	@Autowired
	private Hbwh2Service hbwh2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return hbwh2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HB_WH2_SYSTEM;
	}
	
}