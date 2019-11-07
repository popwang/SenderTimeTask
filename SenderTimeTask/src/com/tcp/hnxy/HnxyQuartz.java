package com.tcp.hnxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class HnxyQuartz  extends AbstactBaseQuartz{
	
	@Autowired
	private HnxyService hnxyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return hnxyService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HN_XY_SYSTEM;
	}
	
}