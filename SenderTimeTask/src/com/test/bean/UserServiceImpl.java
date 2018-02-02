package com.test.bean;

public class UserServiceImpl implements UserService {

	@Override
	public String getName(int id) {
		System.out.println("------getName args are ------"+id);
		return "TOM";
	}

	@Override
	public Integer getAge(int id) {
		System.out.println("------getAge args are------"+id);
		return 10;
	}
}
