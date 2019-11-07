package com.test.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collection {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++) {
			list.add(i);
		}
//		list.forEach(item -> System.out.println(item));
		list.stream().sorted((p1,p2)->-p1.compareTo(p2)).mapToInt(item->item).peek(System.out::println).count();
		
		
//		System.out.println(list.stream().skip(2).limit(5).mapToInt(item->item*2).sum());
		
		/**��Щ������ת��������
		 * distinct ȥ��
		 * mapToInt��map�� �����ݽ���ת��
		 * filter ���������й���
		 * peek ָ�����Ѻ�����ÿ��Ԫ�ض���ָ�������Ѻ���
		 * skip ����Ԫ��
		 * limit �ض�Ԫ��
		 */
		List<Integer> subList = list.stream().mapToInt(item->item).filter(item -> (item%2)==0).peek(System.out::println).limit(5).collect(()->new ArrayList<Integer>(),(list1,item)->list1.add(item),
				(list1,list2)->list1.addAll(list2));
		/**
		 * �ۺϺ���
		 * �ɱ�ۺϣ�collect ���Ͻ�����ռ���һ���µļ����У���3����������ͨ�÷��������Բ������󼯺ϣ�Ҳ���Բ���ԭʼ���ͼ���
		 *    		�������͵ķ�����ֻ�ܲ������󼯺ϣ�����ԭʼ���ͼ��ϣ��ᱨ��
		 * �����ۺϣ�
		 * 			reduce((sum,item)->sum+item).get();����optional��ʹ��get���ֵ��
		 * 			reduce() ֱ�ӷ���ֵ
		 */
		
		System.out.println(subList.parallelStream().reduce(0,(sum,item)->sum+item));
		System.out.println(subList.parallelStream().reduce((sum,item)->sum+item).get());
		System.out.println("**************************************");
		/**
		 * ����������ӡ���ϣ�����ʹ��peek������foreach������һ������ֻ��ִ��һ�Σ�����ж����Ҫ����ִ�еĲ���������ʹ��peek
		 */
		subList.stream().peek(item->System.out.println(item)).count();
		System.out.println("=====================================");
		list.parallelStream().collect(Collectors.toList());
		System.out.println("=====================================");
		
//		System.out.println("sum is:"+Stream.of(1,1,null,2,3,4,null,5,6,7,8,9,10).filter(num -> num != null).
//		            distinct().mapToInt(num -> num * 2).
//		            peek(System.out::println).skip(2).limit(4).sum());
		System.out.println("=====================================");
		/**
		 * ������
		 * iterate ����������Ԫ��Ϊ1�����μ�1��ָ��һ����Ԫ�أ�ʹ�ú���ķ�ʽ����һ����
		 */
		System.out.println(Stream.iterate(1, item->item+1).limit(10).mapToInt(item->item).peek(System.out::println).sum());
		/**
		 * ��������ʹ��һ�������ĺ���
		 */
		System.out.println(Stream.generate(Math::random).limit(10).peek(System.out::println).count());
		/**
		 * ʹ�ù̶���ֵ�������
		 */
		Stream.generate(()->1).limit(10).peek(System.out::println).count();
		/**
		 * �ռ���list��set��map�У�ʹ��collectors.toList/toSet/toMap����
		 */
		List<Integer> list2 = Stream.iterate(1, item->item*2).limit(10).collect(Collectors.toList());
		/**
		 * foreach������void���͵ģ��޷�ʹ����ʽ�﷨��
		 * max������Ҫ��һ��compartor�ӿڵ�ʵ�ַ���
		 * ����
		 * ����������optional��ʹ��get��ȡ��ʵֵ
		 */
		System.out.println(list2.stream().peek(System.out::println).max((p1,p2)->-p1.compareTo(p2)).get());
		
	}
}
