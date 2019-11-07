package com.test.aop.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.test.aop.utils.TransactionManager;

public class ProxyFactoryBean {
	//֪ͨ
	private TransactionManager txManager;
	//Ŀ�����
	private Object target;

	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
	//jdk�������ɴ�����
	//����Ŀ�����target��Ϊ��װ���֪ͨ�����ش������
	public Object getProxy() {
		Object proxy = Proxy.newProxyInstance(
				target.getClass().getClassLoader(),/*1.�������*/
				target.getClass().getInterfaces(), /*2.Ŀ�����ʵ�ֵĽӿ�*/
				new InvocationHandler() {/*3.InvocationHandler*/
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						try {
							//1.��������
							txManager.beginTransaction();
							//2.ִ�в���
							Object retVal = method.invoke(target, args);
							//3.�ύ����
							txManager.commit();
							//4.���ؽ��
							return retVal;
						} catch (Exception e) {
							//5.�ع�����
							txManager.rollback();
							throw new RuntimeException(e);
						} finally {
							//6.�ͷ�����
							txManager.release();
						}

					}
				}
		);
		return proxy;
	}

}