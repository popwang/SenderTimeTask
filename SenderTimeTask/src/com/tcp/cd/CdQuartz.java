package com.tcp.cd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class CdQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private CdService cdService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return cdService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SC_CD_SYSTEM;
	}

}
