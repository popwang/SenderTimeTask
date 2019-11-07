package com.test.aop;

import com.test.aop.factory.BeanFactory;
/**
 * ʹ�ö�̬����ʵ��AOP���ܣ�
 * 1.����@Transactionע�⣻
 * 2.����beanfactory��getbean�����������ȫ����ʹ�÷����ж������Ƿ���@Transactionע�⣻
 *   1���������ʹ��proxy��������һ����ǿ��service�࣬��service�����������manager�࣬�Բ���������ǿ��
 *   2������ͷ��ص�ǰ��ʵ���ࣻ
 * 3.Ϊ��ʵ��������Ҫ����һ����������������д������������ύ���ع����ͷŲ�����
 * 4.���������Ҫ��֤���ݿ�������ͬһ������Ҫ����һ�����ݿ����ӵĹ����࣬�����ӷŽ�threadlocal�С�
 * 5.��ʹ��spring����bean�����ڽ������ģʽʱ����Ҫ����Ա����׼��һ��set�����������ṩ����Ա�����Ĺ��캯����
 * @author 27438
 *
 */
public class AopTest {
	
	public static void main(String[] args) {
		BeanFactory beanFactory = new BeanFactory();
		try {
			Object object = beanFactory.getBean("com.test.aop.service.UserServiceImpl");
			System.out.println(object.getClass().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
