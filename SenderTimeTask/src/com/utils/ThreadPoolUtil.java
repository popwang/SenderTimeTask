package com.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadPoolUtil {
	/**
	 * �̳߳��߳�������ʹ��fixedThreadPool,��������=�������
	 */
	private static int CORE_THREAD_COUNT = 30;
	public static Log log = LogFactory.getLog(ThreadPoolUtil.class);
	private static ScheduledExecutorService  service = null;
	/**
	 * ��ȡȫ�ֵ��̳߳�
	 * @return
	 */
	public static ScheduledExecutorService  getExecutorService(){
		if(service!=null){
			ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
			if(!executor.isShutdown() && executor.getQueue().size()>300){
				service.shutdownNow();
				service = Executors.newScheduledThreadPool(CORE_THREAD_COUNT);
			}
		}else{
			service = Executors.newScheduledThreadPool(CORE_THREAD_COUNT);
		}
		return service;
	}
	/**
	 * �����ǰ�̳߳�״̬��Ϣ
	 */
	public static void printExecutorStatus(){
		ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
		log.info("��ǰ��߳�����"+executor.getActiveCount());
		log.info("��ǰ�Ŷ���������"+executor.getQueue().size());
		log.info("��ǰ�����������"+executor.getCompletedTaskCount());
		log.info("��ǰ����������"+executor.getTaskCount());
	}
	
}
