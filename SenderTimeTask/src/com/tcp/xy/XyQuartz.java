package com.tcp.xy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * 信阳数据对接，暂停开发
 * @author pactera
 *
 */
@Component("xyQuartz")
public class XyQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(XyQuartz.class);
	@Autowired
	private XyServer xyServer;
	@Override
	public void startTimeTask() {
		log.info("信阳数据开始发送...");
		xyServer.handler();
		log.info("信阳数据发送完成！");
	}

}
