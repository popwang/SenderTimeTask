package com.tcp.zz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
@Component
public class ZztbjQuartz  extends AbstactBaseQuartz{
	public static Log log = LogFactory.getLog(ZztbjQuartz.class);
	@Autowired
	private ZztbjService service;

	@Override
	public AbstractBaseService getAbstractBaseService() {
		return service;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.ZZ_TBJ_SYSTEM;
	}
}
