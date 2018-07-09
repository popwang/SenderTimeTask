package com.http.cq;

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
public class CqQuartz extends AbstactBaseQuartz{

	@Autowired
	private CqService cqService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return cqService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SC_CQ_SYSTEM;
	}

}
