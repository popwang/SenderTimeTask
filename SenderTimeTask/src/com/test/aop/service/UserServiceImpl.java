package com.test.aop.service;

import com.test.aop.anno.MyTransactionl;

//@MyTransactionl
public class UserServiceImpl implements UserService {

	@Override
	public void getUser() {
		System.out.println("service÷¥––...");
	}
}
