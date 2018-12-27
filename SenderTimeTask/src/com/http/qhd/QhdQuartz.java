package com.http.qhd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
/**
 * 秦皇岛接口对接定时器
 * @author pactera
 */
@Component
public class QhdQuartz extends AbstactBaseQuartz{

	@Autowired
	private QhdService qhdService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return qhdService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.SC_CQ2_SYSTEM;
	}

}
