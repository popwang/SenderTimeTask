package com.tcp.sxwn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class SxwnQuartz extends AbstactBaseQuartz{

	@Autowired
	private SxwnService sxwnService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return sxwnService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SX_WN_SYSTEM;
	}
	
}
