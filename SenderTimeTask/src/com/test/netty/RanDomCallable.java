package com.test.netty;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//1.����һ���߳��࣬ʵ��Callable�ӿڣ�
//2.Callable�ᱨһ����ɫ�ľ��棬ԭ�򣺿��ԼӸ����ͣ�������ͣ��Ƿ���ֵ��Ӧ�����͡�
//3.����������ǲ�������������ԼӸ�����Integer
public class RanDomCallable implements Callable<Integer> {
	//4.һ������ķ���ȷ���ˣ���ô�����д�ķ����ķ���ֵ���;���Integer�ˡ�
	public Integer call() throws Exception {
		Thread.sleep(2000);
		return new Random().nextInt(10);
	}
	//5.дmain��������
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//6.����һ���̶߳���
		RanDomCallable rdc=new RanDomCallable();
		//7.�����̣߳�ֱ����Thread��rdc���У������ٽ���һ��FutureTask
		FutureTask<Integer> ft=new FutureTask<Integer>(rdc);
		Thread t=new Thread(ft);//FutureTaskʵ����Runnable�ӿ����Կ��Դ���
		t.start();
		//8.�����Ѿ����߳������ˣ�ֱ��������û�н����
		//9.���Ǳ���Ҫ�Է���ֵ������ô��ν��շ���ֵ��
		Integer i = ft.get();//get�����п��Լ���sleep,��֤get�Ǹ���������
		//10.�ж��߳��Ƿ�ִ�н�����
		System.out.println(ft.isDone());
		System.out.println(i);
		System.out.println(ft.isDone());
	}
}
