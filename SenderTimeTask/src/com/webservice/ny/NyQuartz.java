package com.webservice.ny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
/**
 * 南阳对接定时器
 * @author pactera
 */
@Component
public class NyQuartz extends AbstactBaseQuartz{
	@Autowired
	private NyService nyService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return nyService;
	}
	
	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HA_NY_SYSTEM;
	}

}
