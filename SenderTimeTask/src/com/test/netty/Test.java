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
		//线程池的核心线程数：3个 
		ExecutorService es = Executors.newFixedThreadPool(100);
		for (int i = 1; i <=100; i++) {
			//提交任务之后，返回结果是Future，此时线程还没有结束，等待任务的返回值呢 
			Future<String> f = es.submit(new TestThread());
//			//输出返回值
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
