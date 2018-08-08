package com.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {
	private static final ExecutorService exec = Executors.newFixedThreadPool(30);
	
	public static ExecutorService getExecutorService(){
		return exec;
	}
}
