package com.test;

public class TestReturn {

	public static void main(String[] args) {    
		  System.out.println(test());
		  System.out.println("finish..");
	}
	public static int test() {
	  int b = 20;
	  try {
	   System.out.println("try block");
	   return b += 80;
	  }
	  finally {
	   System.out.println("finally block");
	   if (b > 20) {
	    System.out.println("b = " + b);
	   }
	     //������ȡ��ע�ͣ��μ�����2��
		     return 1;
	     //������ȡ��ע�ͣ��μ����3��
	     //b --; // �����ķ���ֵ��finally֮ǰ�Ѿ����ˣ��������Ϊÿ����������һ����������returnֵ���ڴ�ռ䣩���ڴ����޸�b������Ӱ�췵��ֵ��
	  }
	 }
}
