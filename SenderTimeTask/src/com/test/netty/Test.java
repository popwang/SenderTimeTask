package com.test.netty;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Test {
	   public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		long start = System.currentTimeMillis();
		//�̳߳صĺ����߳�����3�� 
		ExecutorService es = Executors.newFixedThreadPool(100);
		for (int i = 1; i <=100; i++) {
			//�ύ����֮�󣬷��ؽ����Future����ʱ�̻߳�û�н������ȴ�����ķ���ֵ�� 
			Future<String> f = es.submit(new TestThread());
//			//�������ֵ
//			System.out.println(f.get());
		}
		es.shutdown();
		long end = System.currentTimeMillis();
		System.out.println("cost time:"+(end-start)/1000);
	}
}

class TestThread implements Callable<String>{
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		int a=new Random().nextInt(10);
		return Thread.currentThread().getName()+"==="+a;
	}
}
