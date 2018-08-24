package com.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcp.xabq.XabqService;
import com.tcp.xabq2.Xabq2Service;
/**
 * ��ҵ����в�֣�ʱ����1�������ң���ʱ�����Ƚ����е�ҵ�����applicationContext1.xml
 * ��֤�����ϴ��ļ�ʱ��
 * @author pactera
 */
public class AppMain1 {
	public static Log log = LogFactory.getLog(AppMain1.class);
	/**
	 * @ spring app��ڷ���
	 * @param args    
	 * @return void   	
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext1.xml");
		log.info("�̼����ʱ����������������");
		XabqService bq = (XabqService)factory.getBean("xabqService");
		Xabq2Service bq2 = (Xabq2Service)factory.getBean("xabq2Service");
		log.info("�������ʱ����ʱ����������������");
		ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
		schedule.scheduleAtFixedRate(bq, 0, 90, TimeUnit.SECONDS);
		schedule.scheduleAtFixedRate(bq2, 0, 90, TimeUnit.SECONDS);
	}

}
