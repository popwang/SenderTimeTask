package com.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.common.QuartzInterface;
import com.utils.SystemEnum;

public abstract class AbstactBaseQuartz implements QuartzInterface {
	
	public static Log log = LogFactory.getLog(AbstactBaseQuartz.class);

	@Override
	public void startTimeTask() {
		AbstractBaseService service = getAbstractBaseService();
		SystemEnum systemEnum = getSystemEnum();
		log.info(systemEnum.getName()+"数据开始发送...");
		service.handler(systemEnum);
		log.info(systemEnum.getName()+"发送完成！");
	}
	/**
	 * 定时器的实现类决定注入那个service的业务调用
	 * @return
	 */
	public abstract AbstractBaseService getAbstractBaseService();
	/**
	 * 设定接口的枚举类型
	 * @return
	 */
	public abstract SystemEnum getSystemEnum();
}
