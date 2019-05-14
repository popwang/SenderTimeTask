package com.tcp.zkhy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class ZkhyQuartz  extends AbstactBaseQuartz{
	@Autowired
	private ZkhyService zkhyService;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return zkhyService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZK_HY_SYSTEM;
	}
}
