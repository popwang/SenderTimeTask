package com.test.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test5 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//线程池的核心线程数：3个 
		ExecutorService es = Executors.newFixedThreadPool(50);
		//List集合用来存储Future
		List<Future> list=new ArrayList<Future>();
		for (int i = 1; i <=100; i++) {
			//提交任务之后，返回结果是Future，此时线程还没有结束，等待任务的返回值呢 
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
