package com.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
		@SuppressWarnings("unused")
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext1.xml");
		log.info("�̼����ʱ����������������");
	}

}
