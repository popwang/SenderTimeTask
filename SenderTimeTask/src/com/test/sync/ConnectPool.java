package com.test.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 模拟数据库连接池的实现，假设现在数据库连接池最大连接数为3，
 * 当3个连接都分配出去以后，现在有用户继续请求连接，需要等待其他连接的释放
 * 使用Semaphore模拟数据库链接池的使用
 * @author 
 * 
 */
public class ConnectPool {
    private final List<Conn> pool = new ArrayList<Conn>(3);
    private final Semaphore semaphore = new Semaphore(3);// 定义由信号量所允许的最大线程数为3

    /**
     * 初始化分配3个连接
     */
    public ConnectPool() {
        pool.add(new Conn());
        pool.add(new Conn());
        pool.add(new Conn());
    }

    /**
     * 请求分配连接
     * 
     * @return
     * @throws InterruptedException
     */
    public Conn getConn() throws InterruptedException {
        semaphore.acquire();// 请求许可 。要么通过然后将信号量减一，要么一直等下去，直到信号量大于一或超时
        Conn c = null;
        synchronized (pool) {
            c = pool.remove(0);
        }
        System.out.println(Thread.currentThread().getName() + " get a conn "
                + c);
        return c;
    }

    /**
     * 释放连接
     * 
     * @param c
     */
    public void release(Conn c) {
        pool.add(c);
        System.out.println(Thread.currentThread().getName()
                + " release a conn " + c);
        semaphore.release();// 释放许可。也就是释放了由信号量守护的资源
    }

    public static void main(String[] args) {
        final ConnectPool pool = new ConnectPool();
        /**
         * 第一个线程占用1个连接3秒
         */
        new Thread() {
            public void run() {
                try {
                    Conn c = pool.getConn();
                    Thread.sleep(3000);
                    pool.release(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
        /**
         * 开启3个线程请求分配连接
         */
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    try {
                        Conn c = pool.getConn();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }
    }
    
    private class Conn {
    	
    }
}
