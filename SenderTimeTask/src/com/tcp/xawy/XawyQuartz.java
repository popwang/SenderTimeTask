package com.tcp.xawy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class XawyQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private XawyService xawyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xawyService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_WY_SYSTEM;
	}

}
