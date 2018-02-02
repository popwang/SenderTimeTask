package com.test.proxy;

import com.test.bean.UserService;
import com.test.bean.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl(); 
		MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);  
//        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),  
//                userService.getClass().getInterfaces(), invocationHandler);
        UserService userServiceProxy = (UserService)invocationHandler.bind(userService);
        System.out.println(userServiceProxy.getName(1));
        System.out.println(userServiceProxy.getAge(1)); 
	}
}
