package com.test.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test5 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//�̳߳صĺ����߳�����3�� 
		ExecutorService es = Executors.newFixedThreadPool(50);
		//List���������洢Future
		List<Future> list=new ArrayList<Future>();
		for (int i = 1; i <=100; i++) {
			//�ύ����֮�󣬷��ؽ����Future����ʱ�̻߳�û�н������ȴ�����ķ���ֵ�� 
			Future<String> s = es.submit(new TestThread());
			list.add(s);
		}
		System.out.println("asf");
		for (Future f : list) {
			System.out.println(f.get());
		}
		es.shutdown();
	}
}
