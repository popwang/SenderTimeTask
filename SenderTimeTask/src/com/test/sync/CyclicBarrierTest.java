package com.test.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环栅栏
 * 学生一个人走太危险，现在门卫放学在门口守着，让学生3个一组的走，不到3个人不允许走。
 * @author pactera
 *
 */
public class CyclicBarrierTest {
    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 11;
    /**
     * 当人到齐，自动开门程序
     */
    final CyclicBarrier barrier = new CyclicBarrier(3,
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("有3个学生到齐了，放行....");
                }
            });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + "回家了~");
    }

    public static void main(String[] args) throws InterruptedException,
            BrokenBarrierException {
        final CyclicBarrierTest instance = new CyclicBarrierTest();
        /**
         * 每个线程代表一个学生
         */
        for (int i = 1; i <= instance.STUDENT_COUNT; i++) {
            new Thread("学生" + i + "  ") {
                public void run() {
                    try {
                        instance.goHome();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }

    }
}
