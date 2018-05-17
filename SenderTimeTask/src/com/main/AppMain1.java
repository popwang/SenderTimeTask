package com.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 对业务进行拆分，时间间隔1分钟左右，对时间间隔比较敏感的业务放入applicationContext1.xml
 * 保证数据上传的及时性
 * @author pactera
 */
public class AppMain1 {
	public static Log log = LogFactory.getLog(AppMain1.class);
	/**
	 * @ spring app入口方法
	 * @param args    
	 * @return void   
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext1.xml");
		log.info("短间隔定时任务已启动。。。");
	}

}
