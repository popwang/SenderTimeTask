package com.tcp.zksq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class ZksqQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private ZksqService zksqService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zksqService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZK_SQ_SYSTEM;
	}

}
