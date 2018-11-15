package com.tcp.zj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class ZjQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private ZjService zjService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zjService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_ZJ_SYSTEM;
	}
	
}
