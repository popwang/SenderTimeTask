package com.webservice.lyln;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class LylnQuartz extends AbstactBaseQuartz{

	@Autowired
	private LylnService lylnService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return lylnService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.LY_LN_SYSTEM;
	}

}
