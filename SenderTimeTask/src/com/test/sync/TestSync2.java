package com.test.sync;

import java.util.Timer;
import java.util.TimerTask;

public class TestSync2 implements Runnable {
	int  b = 100;
	synchronized void m1() throws InterruptedException{
		b = 1000;
//		Thread.sleep(5000);//6
		System.out.println("b="+b);
	}
	
	synchronized void m2() throws InterruptedException{
		b = 2000;
//		System.out.println("m2="+b);
	}
	
	public static void main(String[] args) throws InterruptedException{
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				TestSync2 tt = new TestSync2();
				Thread t = new Thread(tt);//1
				t.start();//2
				try {
					tt.m2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//3
				System.out.println("main thread b="+tt.b);//4
			}
		}, 1000,1000);
	}
	
	@Override
	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
