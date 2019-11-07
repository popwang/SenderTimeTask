package com.test.aop.factory;

import com.test.aop.utils.ConnectionUtils;
import com.test.aop.utils.TransactionManager;
import com.test.aop.anno.MyTransactionl;
public class BeanFactory {

	public Object getBean(String name) throws Exception {
		//得到目标类的Class对象
		Class<?> clazz = Class.forName(name);
		//得到目标对象
		Object bean = clazz.newInstance();
		//得到目标类上的@MyTransactional注解
		MyTransactionl myTransactional = clazz.getAnnotation(MyTransactionl.class);
		//如果打了@MyTransactional注解，返回代理对象，否则返回目标对象
		if (null != myTransactional) {
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			TransactionManager txManager = new TransactionManager();
			txManager.setConnectionUtils(new ConnectionUtils());
			//装配通知和目标对象
			proxyFactoryBean.setTxManager(txManager);
			proxyFactoryBean.setTarget(bean);
			Object proxyBean = proxyFactoryBean.getProxy();
			//返回代理对象
			return proxyBean;
		}
		//返回目标对象
		return bean;
	}
}
