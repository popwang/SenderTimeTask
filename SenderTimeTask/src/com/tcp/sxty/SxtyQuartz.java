package com.tcp.sxty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class SxtyQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private SxtyService sxtyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return sxtyService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SX_TY_SYSTEM;
	}

}
