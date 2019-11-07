package com.test.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.test.bean.UserService;
import com.test.bean.UserServiceImpl;

public class Main {

	public static void main(String[] args) throws Exception {
		UserServiceImpl userService = new UserServiceImpl(); 
//		MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);  
//        UserService userServiceProxy = (UserService)invocationHandler.getProxy(userService);
//        System.out.println(userServiceProxy.getName(1));
//        System.out.println(userServiceProxy.getAge(1)); 
       
		
		UserService userProxy = (UserService)getProxy(userService);
		userProxy.getAge(11);
		userProxy.getName(12);
	}
	
	private static Object getProxy(final Object target) throws Exception{
		Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
		Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
		
		Object userProxy = constructor.newInstance(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName()+"+++++++++++++log.start+++++++++++++++++");
				Object object = method.invoke(target, args);
				System.out.println(method.getName()+"+++++++++++++log.end+++++++++++++++++");
				return object;
			}
		});
		return userProxy;
	}
}
