package com.main;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcp.xabq2.Xabq2Service;
import com.tcp.zz.ZztbjService;
import com.utils.SystemEnum;
import com.webservice.ny.NyService;
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
		NyService bq = (NyService)factory.getBean("nyService");
		bq.handler(SystemEnum.HA_NY_SYSTEM);
	}

}
