package com.test.sync;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ģ��һ��������˵Ĺ��̣����߳��Ѿ���������ʻ����ܶ��ˣ�
 * Ϊ�˲������̵߳ȴ� PrivateAccount��ļ������ķ��ض������µ��߳�ȥ���� 
 * ��ʹ�� FutureTask��������أ����������̻߳����Լ������������飬 
 * �����Ҫ�����ܶ��ʱ���ٳ���ȥ���privateAccount ����Ϣ��
 * @author Administrator
 */
@SuppressWarnings("all")
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException {
        // ��ʼ��һ��Callable�����FutureTask����
        Callable pAccount = new PrivateAccount();
        FutureTask futureTask = new FutureTask(pAccount);
        // ʹ��futureTask����һ���߳�
        Thread pAccountThread = new Thread(futureTask);
        System.out.println("===futureTask�߳����ڿ�ʼ����������ʱ��Ϊ��" + System.nanoTime());
        pAccountThread.start();
        System.out.println("���߳̿�ʼִ����������");
        // �������˻���ȡ�ܽ��
        Thread.sleep(1000);
        int totalMoney = new Random().nextInt(100000);
        System.out.println("�������������˻��е��ܽ��Ϊ" + totalMoney);
        System.out.println("�ȴ�˽���˻��ܽ��ͳ�����...");
        // ���Ժ�̨�ļ����߳��Ƿ���ɣ����δ�����ȴ�
        while (!futureTask.isDone()) {
            try {
                Thread.sleep(500);
                System.out.println("===˽���˻�����δ��ɼ����ȴ�...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===futureTask�̼߳�����ϣ���ʱʱ��Ϊ" + System.nanoTime());
        Integer privateAccountMoney = null;
        try {
            privateAccountMoney = (Integer) futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("�����ڵ��ܽ��Ϊ��" + totalMoney + privateAccountMoney.intValue());
    }
}

@SuppressWarnings("all")
class PrivateAccount implements Callable {
    Integer totalMoney;

    @Override
    public Object call() throws Exception {
        System.out.println("===��ʼ�鿴˽���˻���");
        Thread.sleep(5000);
        totalMoney = new Integer(new Random().nextInt(10000));
        System.out.println("===����ǰ��" + totalMoney + "������˽���˻���");
        return totalMoney;
    }
}