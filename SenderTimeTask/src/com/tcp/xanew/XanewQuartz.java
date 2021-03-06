package com.tcp.xanew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class XanewQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private XanewService xanewService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return xanewService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.XA_GXQ_SYSTEM;
	}
}
