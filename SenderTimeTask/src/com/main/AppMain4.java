package com.main;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 对业务进行拆分，将灞桥区子平台放入applicationContext3.xml
 * 保证数据上传的及时性
 * @author pactera
 */
public class AppMain4 {
	public static Log log = LogFactory.getLog(AppMain4.class);
	/**
	 * @ spring app入口方法
	 * @param args    
	 * @return void   	
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext4.xml");
		log.info("特必佳定时任务已启动。。。");
	}

}
