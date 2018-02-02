package com.tcp.xadx;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * 西安大兴区定时任务
 * @author Administrator
 */
@Component
public class XadxQuartz implements QuartzInterface {

	public static Log log = LogFactory.getLog(XadxQuartz.class);
	@Autowired
	private XadxService service;
	
	@Override
	public void startTimeTask() {
		log.info("西安大兴区数据发送开始...");
		service.handler();
		log.info("西安大兴区本轮数据发送完成！");
	}

}
