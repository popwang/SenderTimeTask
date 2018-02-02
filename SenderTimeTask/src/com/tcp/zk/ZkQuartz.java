package com.tcp.zk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.QuartzInterface;
/**
 * �ܿ����ݷ��ͽӿ������
 * @author pactera
 *
 */
@Component("zkQuartz")
public class ZkQuartz implements QuartzInterface {
	public static Log log = LogFactory.getLog(ZkQuartz.class);
	@Autowired
	private ZkServer xyServer;
	@Override
	public void startTimeTask() {
		log.info("�ܿ����ݿ�ʼ����...");
		xyServer.handler();
		log.info("�ܿ����ݷ�����ɣ�");
	}

}
