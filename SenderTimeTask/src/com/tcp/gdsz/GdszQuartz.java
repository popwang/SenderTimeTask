package com.tcp.gdsz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class GdszQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private GdszService gdszService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return gdszService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.GD_SZ_SYSTEM;
	}

}
