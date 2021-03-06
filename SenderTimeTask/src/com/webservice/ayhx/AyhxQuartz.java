package com.webservice.ayhx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component("ayhxQuartz")
public class AyhxQuartz extends AbstactBaseQuartz{
	@Autowired
	private AyhxService ayhxService;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return ayhxService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.AY_HX_SYSTEM;
	}
	
}
