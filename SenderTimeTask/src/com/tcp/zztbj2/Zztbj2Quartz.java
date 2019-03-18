package com.tcp.zztbj2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
@Component
public class Zztbj2Quartz  extends AbstactBaseQuartz{
	public static Log log = LogFactory.getLog(Zztbj2Quartz.class);
	@Autowired
	private Zztbj2Service service;

	@Override
	public AbstractBaseService getAbstractBaseService() {
		return service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_TBJ2_SYSTEM;
	}
}
