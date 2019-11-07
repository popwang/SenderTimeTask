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
		
		/**这些都属于转换函数：
		 * distinct 去重
		 * mapToInt（map） 将数据进行转换
		 * filter 按条件进行过滤
		 * peek 指定消费函数，每个元素都被指定给消费函数
		 * skip 丢弃元素
		 * limit 截断元素
		 */
		List<Integer> subList = list.stream().mapToInt(item->item).filter(item -> (item%2)==0).peek(System.out::println).limit(5).collect(()->new ArrayList<Integer>(),(list1,item)->list1.add(item),
				(list1,list2)->list1.addAll(list2));
		/**
		 * 聚合函数
		 * 可变聚合：collect 如上将结果收集到一个新的集合中，有3个参数的是通用方法，可以操作对象集合，也可以操作原始类型集合
		 *    		简略类型的方法，只能操作对象集合，操作原始类型集合，会报错
		 * 其他聚合：
		 * 			reduce((sum,item)->sum+item).get();返回optional，使用get获得值；
		 * 			reduce() 直接返回值
		 */
		
		System.out.println(subList.parallelStream().reduce(0,(sum,item)->sum+item));
		System.out.println(subList.parallelStream().reduce((sum,item)->sum+item).get());
		System.out.println("**************************************");
		/**
		 * 如果想遍历打印集合，可以使用peek函数，foreach函数在一个流上只能执行一次，如果有多个需要遍历执行的操作，可以使用peek
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
		 * 创建流
		 * iterate 生成流，首元素为1，依次加1；指定一个首元素，使用后面的范式生成一个流
		 */
		System.out.println(Stream.iterate(1, item->item+1).limit(10).mapToInt(item->item).peek(System.out::println).sum());
		/**
		 * 创建流，使用一个给定的函数
		 */
		System.out.println(Stream.generate(Math::random).limit(10).peek(System.out::println).count());
		/**
		 * 使用固定的值填充序列
		 */
		Stream.generate(()->1).limit(10).peek(System.out::println).count();
		/**
		 * 收集到list，set，map中，使用collectors.toList/toSet/toMap方法
		 */
		List<Integer> list2 = Stream.iterate(1, item->item*2).limit(10).collect(Collectors.toList());
		/**
		 * foreach函数是void类型的，无法使用链式语法；
		 * max方法需要传一个compartor接口的实现方法
		 * 或者
		 * 返回类型是optional，使用get获取真实值
		 */
		System.out.println(list2.stream().peek(System.out::println).max((p1,p2)->-p1.compareTo(p2)).get());
		
	}
}
