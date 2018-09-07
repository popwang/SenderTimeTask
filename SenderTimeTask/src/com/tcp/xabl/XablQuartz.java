package com.tcp.xabl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
@Component
public class XablQuartz extends AbstactBaseQuartz{

	@Autowired
	private XablService xablService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xablService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_BL_SYSTEM;
	}


}
