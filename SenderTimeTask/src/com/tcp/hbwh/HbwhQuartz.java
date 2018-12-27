package com.tcp.hbwh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
/**
 * 湖北武汉平台定时器
 * @author 27438
 *
 */
@Component
public class HbwhQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private HbwhService hbwhService;
	
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return hbwhService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HB_WH_SYSTEM;
	}
	
}
