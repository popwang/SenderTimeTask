package com.tcp.lymj2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class Lymj2Quartz extends AbstactBaseQuartz{
	@Autowired
	private Lymj2Service lymj2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return lymj2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.LY_MJ2_SYSTEM;
	}

}
