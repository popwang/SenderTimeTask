package com.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * �������1�������ϵģ��Լ�������е�ҵ�����applicationContext2.xml
 * @author pactera
 *
 */
public class AppMain2 {
	public static Log log = LogFactory.getLog(AppMain2.class);
	/**
	 * @ spring app��ڷ���
	 * @param args    
	 * @return void   
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("unused")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext2.xml");
		log.info("�������ʱ����������������");
	}

}
