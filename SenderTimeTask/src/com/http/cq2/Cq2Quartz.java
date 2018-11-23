package com.http.cq2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
/**
 * 重庆对接定时器
 * @author pactera
 */
@Component
public class Cq2Quartz extends AbstactBaseQuartz{

	@Autowired
	private Cq2Service cq2Service;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return cq2Service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SC_CQ2_SYSTEM;
	}

}
