package com.tcp.zz6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
@Component
public class Zz6Quartz extends AbstactBaseQuartz{
	
	@Autowired
	private Zz6Service zz6Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zz6Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_SIX_SYSTEM;
	}

}
