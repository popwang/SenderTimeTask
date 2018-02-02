package com.test.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * cglibʹ��������ģʽ
 * @author pactera
 *
 */
public class CglibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("++++++before " + arg3.getSuperName() + "++++++");
		Object obj1 = arg3.invokeSuper(arg0, arg2);
		System.out.println("++++++after " + arg3.getSuperName() + "++++++");
		return obj1;
	}

}
