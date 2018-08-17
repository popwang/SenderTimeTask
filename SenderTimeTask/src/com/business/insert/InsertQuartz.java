package com.business.insert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

@Component
public class InsertQuartz extends AbstactBaseQuartz{
	@Autowired
	private InsertService insertService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return insertService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.INSERT_SYSTEM;
	}
	
}
