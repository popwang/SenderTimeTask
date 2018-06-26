package com.http.zxline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class ZxlineQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private ZxlineService zxlineService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zxlineService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_XC_SYSTEM;
	}

}
