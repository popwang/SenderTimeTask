package com.http.nj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class NjQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private NjService njService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return njService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.JS_NJ_SYSTEM;
	}

}
