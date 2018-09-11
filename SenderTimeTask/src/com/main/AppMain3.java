package com.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcp.xabq2.Xabq2Service;
/**
 * 对业务进行拆分，将灞桥区子平台放入applicationContext3.xml
 * 保证数据上传的及时性
 * @author pactera
 */
public class AppMain3 {
	public static Log log = LogFactory.getLog(AppMain3.class);
	/**
	 * @ spring app入口方法
	 * @param args    
	 * @return void   	
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext3.xml");
		log.info("短间隔定时任务已启动。。。");
		Xabq2Service bq2 = (Xabq2Service)factory.getBean("xabq2Service");
		log.info("灞桥区子平台定时任务定时任务已启动。。。");
		ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
		schedule.scheduleAtFixedRate(bq2, 0, 30, TimeUnit.SECONDS);
	}

}
