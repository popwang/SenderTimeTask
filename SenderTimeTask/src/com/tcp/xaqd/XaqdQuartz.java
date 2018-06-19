package com.tcp.xaqd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
@Component
public class XaqdQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private XaqdService xaqdService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xaqdService;
	}
	
	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_QD_SYSTEM;
	}

}
