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
		@SuppressWarnings("resource")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContextTest.xml");
		Xabq2Service bq = (Xabq2Service)factory.getBean("xabq2Service");
		bq.handler("00001000");
	}

}