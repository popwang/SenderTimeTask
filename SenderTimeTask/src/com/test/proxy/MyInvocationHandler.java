package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdkʹ��invokeģʽ������
 * @author pactera
 *
 */
public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler() {
		super();
	}

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}
	
	/**
	 * ����������������δ���ʵ��ϸ�ڣ�ʹҵ�������Ӽ������
	 * @param target
	 * @return
	 */
	public Object getProxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("++++++before " + method.getName() + "++++++");
		Object result = method.invoke(target, args);
		System.out.println("++++++after " + method.getName() + "++++++");
		return result;
	}

}
