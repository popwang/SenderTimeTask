package com.http.zz2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Zz2Quartz extends AbstactBaseQuartz{
	@Autowired
	private Zz2Service zz2Service;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zz2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_ZB_SYSTEM;
	}
	
}
