package com.http.zxline2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Zxline2Quartz extends AbstactBaseQuartz{
	
	@Autowired
	private Zxline2Service zxline2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zxline2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_XC2_SYSTEM;
	}

}
