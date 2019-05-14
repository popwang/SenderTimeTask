package com.tcp.smls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class SmlsQuartz extends AbstactBaseQuartz{
	@Autowired
	private SmlsService smlsService;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return smlsService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SM_LS_SYSTEM;
	}

}
