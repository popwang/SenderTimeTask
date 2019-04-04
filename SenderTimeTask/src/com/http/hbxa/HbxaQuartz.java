package com.http.hbxa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.AbstactBaseQuartz;
import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;

/**
 * 河北雄安定时器
 * @author 27438
 *
 */
@Component
public class HbxaQuartz extends AbstactBaseQuartz{
	
	@Autowired
	private HbxaService hbxaService;
	@Override
	public AbstractBaseService getAbstractBaseService() {
		return hbxaService;
	}

	@Override
	public SystemEnum getSystemEnum() {
		return SystemEnum.HB_XA_SYSTEM;
	}

}
