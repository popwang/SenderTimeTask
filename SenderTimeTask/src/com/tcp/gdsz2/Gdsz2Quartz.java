package com.tcp.gdsz2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Gdsz2Quartz extends AbstactBaseQuartz{
	
	@Autowired
	private Gdsz2Service gdsz2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return gdsz2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.GD_SZ2_SYSTEM;
	}

}
