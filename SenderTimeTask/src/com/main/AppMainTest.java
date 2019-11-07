package com.main;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcp.gdsz2.Gdsz2Service;
import com.utils.SystemEnum;
/**
 * 集成测试类 
 * @author pactera
 */
public class AppMainTest {
	public static Log log = LogFactory.getLog(AppMainTest.class);
	/**
	 * @ spring app入口方法
	 * @param args    
	 * @return void   
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContextTest.xml");
		Gdsz2Service bq = (Gdsz2Service)factory.getBean("gdsz2Service");
		bq.handler(SystemEnum.GD_SZ_SYSTEM);
	}

}
