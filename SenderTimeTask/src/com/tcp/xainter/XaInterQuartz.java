package com.tcp.xainter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class XaInterQuartz extends AbstactBaseQuartz{

	@Autowired
	private XaInterService xaInterService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xaInterService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_INTER_SYSTEM;
	}

}
