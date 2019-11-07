package com.test.aop.factory;

import com.test.aop.utils.ConnectionUtils;
import com.test.aop.utils.TransactionManager;
import com.test.aop.anno.MyTransactionl;
public class BeanFactory {

	public Object getBean(String name) throws Exception {
		//�õ�Ŀ�����Class����
		Class<?> clazz = Class.forName(name);
		//�õ�Ŀ�����
		Object bean = clazz.newInstance();
		//�õ�Ŀ�����ϵ�@MyTransactionalע��
		MyTransactionl myTransactional = clazz.getAnnotation(MyTransactionl.class);
		//�������@MyTransactionalע�⣬���ش�����󣬷��򷵻�Ŀ�����
		if (null != myTransactional) {
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			TransactionManager txManager = new TransactionManager();
			txManager.setConnectionUtils(new ConnectionUtils());
			//װ��֪ͨ��Ŀ�����
			proxyFactoryBean.setTxManager(txManager);
			proxyFactoryBean.setTarget(bean);
			Object proxyBean = proxyFactoryBean.getProxy();
			//���ش������
			return proxyBean;
		}
		//����Ŀ�����
		return bean;
	}
}
