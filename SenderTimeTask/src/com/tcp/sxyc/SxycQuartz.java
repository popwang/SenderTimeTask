package com.tcp.sxyc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class SxycQuartz extends AbstactBaseQuartz{
	
	@Resource(name="sxycService")
	private AbstractBaseService service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SX_YC_SYSTEM;
	}
}
