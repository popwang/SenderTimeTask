package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk使用invoke模式（祈求）
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
	 * 增加这个方法，屏蔽代理实现细节，使业务代码更加简洁清晰
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
