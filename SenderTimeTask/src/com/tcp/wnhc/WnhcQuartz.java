package com.tcp.wnhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class WnhcQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private WnhcService wnhcService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return wnhcService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.WN_HC_SYSTEM;
	}

}
