package com.main;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ��ҵ����в�֣����������ƽ̨����applicationContext3.xml
 * ��֤�����ϴ��ļ�ʱ��
 * @author pactera
 */
public class AppMain4 {
	public static Log log = LogFactory.getLog(AppMain4.class);
	/**
	 * @ spring app��ڷ���
	 * @param args    
	 * @return void   	
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext4.xml");
		log.info("�رؼѶ�ʱ����������������");
	}

}
