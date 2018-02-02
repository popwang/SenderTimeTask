package com.test.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier ѭ��դ��
 * ѧ��һ������̫Σ�գ�����������ѧ���ſ����ţ���ѧ��3��һ����ߣ�����3���˲������ߡ�
 * @author pactera
 *
 */
public class CyclicBarrierTest {
    /**
     * ѧ������
     */
    private final int STUDENT_COUNT = 11;
    /**
     * ���˵��룬�Զ����ų���
     */
    final CyclicBarrier barrier = new CyclicBarrier(3,
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("��3��ѧ�������ˣ�����....");
                }
            });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "��ˢ�����ȴ����Żؼ�~");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + "�ؼ���~");
    }

    public static void main(String[] args) throws InterruptedException,
            BrokenBarrierException {
        final CyclicBarrierTest instance = new CyclicBarrierTest();
        /**
         * ÿ���̴߳���һ��ѧ��
         */
        for (int i = 1; i <= instance.STUDENT_COUNT; i++) {
            new Thread("ѧ��" + i + "  ") {
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
