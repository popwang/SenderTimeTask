package com.tcp.xy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * �������ݶԽӣ���ͣ����
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
		log.info("�������ݿ�ʼ����...");
		xyServer.handler();
		log.info("�������ݷ�����ɣ�");
	}

}
