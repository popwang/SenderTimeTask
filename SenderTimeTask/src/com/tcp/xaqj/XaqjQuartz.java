package com.tcp.xaqj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class XaqjQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private XaqjService xaqjService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xaqjService;
	}
	
	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_QJ_SYSTEM;
	}
}
