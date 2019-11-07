package com.tcp.sxty2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
@Component
public class Sxty2Quartz extends AbstactBaseQuartz{
	
	@Autowired
	private Sxty2Service sxty2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return sxty2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SX_TY2_SYSTEM;
	}

}
