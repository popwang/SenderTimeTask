package com.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadPoolUtil {
	/**
	 * 线程池线程数量，使用fixedThreadPool,核心数量=最大数量
	 */
	private static int CORE_THREAD_COUNT = 30;
	public static Log log = LogFactory.getLog(ThreadPoolUtil.class);
	private static final ExecutorService service = Executors.newFixedThreadPool(CORE_THREAD_COUNT);
	/**
	 * 获取全局的线程池
	 * @return
	 */
	public static ExecutorService getExecutorService(){
		return service;
	}
	/**
	 * 输出当前线程池状态信息
	 */
	public static void printExecutorStatus(){
		ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
		log.info("当前活动线程数："+executor.getActiveCount());
		log.info("当前排队任务数："+executor.getQueue().size());
		log.info("当前完成任务数："+executor.getCompletedTaskCount());
		log.info("当前总任务数："+executor.getTaskCount());
	}
	
}
