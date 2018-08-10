package com.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcp.zz.ZztbjService;
import com.utils.SystemEnum;
/**
 * ���ɲ����� 
 * @author pactera
 */
public class AppMainTest {
	public static Log log = LogFactory.getLog(AppMainTest.class);
	/**
	 * @ spring app��ڷ���
	 * @param args    
	 * @return void   
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext1.xml");
		ZztbjService zz = (ZztbjService)factory.getBean("zztbjService");
		zz.handler(SystemEnum.ZZ_TBJ_SYSTEM);
		Thread.currentThread().join();
		System.exit(0);
	}

}
