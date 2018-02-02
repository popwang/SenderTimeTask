package com.http.sdhz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;

@Component
public class SdhzQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(SdhzQuartz.class);
	@Autowired
	private SdhzService service;
	
	@Override
	public void startTimeTask() {
		log.info("山东菏泽数据发送开始...");
		service.handler();
		log.info("山东菏泽本轮数据发送完成！");
	}

}
