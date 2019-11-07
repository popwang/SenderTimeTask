package com.test.aop;

import com.test.aop.factory.BeanFactory;
/**
 * 使用动态代理，实现AOP功能；
 * 1.定义@Transaction注解；
 * 2.定义beanfactory，getbean方法传入类的全名，使用反射判断类上是否有@Transaction注解；
 *   1）如果有则使用proxy代理生成一个增强的service类，在service类中组合事务manager类，对操作进行增强；
 *   2）否则就返回当前的实现类；
 * 3.为了实现事务，需要定义一个事务管理器，集中处理事务开启，提交，回滚，释放操作；
 * 4.事务管理需要保证数据库连接是同一个，需要定义一个数据库连接的工具类，将连接放进threadlocal中。
 * 5.不使用spring进行bean管理，在进行组合模式时，需要给成员变量准备一个set方法。或者提供带成员变量的构造函数。
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
