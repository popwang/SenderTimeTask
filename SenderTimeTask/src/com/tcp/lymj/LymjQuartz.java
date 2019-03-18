package com.tcp.lymj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class LymjQuartz extends AbstactBaseQuartz{
	@Autowired
	private LymjService lymjService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return lymjService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.LY_MJ_SYSTEM;
	}

}
