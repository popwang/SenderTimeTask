package com.test.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AddThread implements Runnable {
    private List<Double> list;
    private String name;

    public AddThread(List<Double> list,String name) {
        this.list = list;
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; ++i) {
            list.add(Math.random());
            System.out.println(name+"add!");
        }
    }
}
/**
 * 验证使用CopyOnWriteArrayList时，线程并发访问的性能和异常情况
 * @author pactera
 *
 */
public class Test05 {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        es.execute(new AddThread(list,"thread1"));
        es.execute(new AddThread(list,"thread2"));
        es.shutdown();
    }
}
