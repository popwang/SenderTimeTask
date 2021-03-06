package com.tcp.xa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class XaQuartz extends AbstactBaseQuartz{
	@Autowired
	private XaService xaService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xaService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SX_XA_SYSTEM;
	}
}
