package com.test.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task1 implements Runnable{

	@Override
	public void run() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())+" : task1 is running...");
	}
	
}
