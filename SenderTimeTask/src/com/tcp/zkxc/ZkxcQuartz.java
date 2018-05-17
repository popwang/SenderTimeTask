package com.tcp.zkxc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class ZkxcQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private ZkxcService zkxcService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zkxcService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZK_XC_SYSTEM;
	}
	
}
