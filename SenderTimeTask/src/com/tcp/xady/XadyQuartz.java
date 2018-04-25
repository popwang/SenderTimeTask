package com.tcp.xady;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

public class XadyQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private XadyService xadyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xadyService;
	}
	
	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_DY_SYSTEM;
	}

}
