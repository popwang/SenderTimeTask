package com.test.cglib;

import org.springframework.cglib.proxy.Enhancer;

import com.test.bean.UserService;
import com.test.bean.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		CglibProxy cglibproxy = new CglibProxy();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(cglibproxy);
		
		UserService o = (UserService)enhancer.create();
		o.getAge(1);
		o.getName(1);
	}

}
