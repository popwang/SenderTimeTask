package com.test;


/**
 * ��̬�����-��ͨ�����-���캯��
 * 
 * ��̬�����������ʱִ�У�����˫��ί�ɣ��ȼ��ظ����������࣬��˾�̬�ĸ�����ִ�У������ִ�У�
 * Ȼ��ʼִ�и���Ĺ��캯������ͨ�������ִ�У���ִ�й��캯����
 * ��ִ�����๹�캯����ͬ������ͨ�������ִ�У���ִ�й��캯����
 * ���˳��
 * 	��̬�����B
	��̬�����A
	��ͨ�Ĵ����B
	������B
	��ͨ�Ĵ����A
	������A
 * */
public class ClassA extends B{
    public ClassA() {
        super();
        System.out.println("������A");
    }
    {
        System.out.println("��ͨ�Ĵ����A");
    }
    static{
        System.out.println("��̬�����A");
    }

    public static void main(String[] args) {
        new ClassA();
    }
}

class B{
    public B(){
        super();
        System.out.println("������B");
    }
    {
        System.out.println("��ͨ�Ĵ����B");
    }

    static{
        System.out.println("��̬�����B");
    }
}

