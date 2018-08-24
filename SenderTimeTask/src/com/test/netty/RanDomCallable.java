package com.test.netty;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//1.创建一个线程类，实现Callable接口，
//2.Callable会报一个黄色的警告，原因：可以加个泛型，这个泛型，是返回值对应的类型。
//3.我们这个题是产生随机数，所以加个泛型Integer
public class RanDomCallable implements Callable<Integer> {
	//4.一旦上面的泛型确定了，那么这个重写的方法的返回值类型就是Integer了。
	public Integer call() throws Exception {
		Thread.sleep(2000);
		return new Random().nextInt(10);
	}
	//5.写main方法测试
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//6.创建一个线程对象：
		RanDomCallable rdc=new RanDomCallable();
		//7.启动线程，直接用Thread传rdc不行，必须再借助一个FutureTask
		FutureTask<Integer> ft=new FutureTask<Integer>(rdc);
		Thread t=new Thread(ft);//FutureTask实现了Runnable接口所以可以传入
		t.start();
		//8.上面已经将线程启动了，直接运行是没有结果的
		//9.我们必须要对返回值处理，那么如何接收返回值：
		Integer i = ft.get();//get方法中可以加入sleep,验证get是个阻塞方法
		//10.判断线程是否执行结束：
		System.out.println(ft.isDone());
		System.out.println(i);
		System.out.println(ft.isDone());
	}
}
