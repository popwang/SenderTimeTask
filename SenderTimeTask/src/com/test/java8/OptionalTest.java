package com.test.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OptionalTest {
	public static void main(String[] args) {
		User user = new User();
		user.setEmail("abc@qq.com");
		user.setName("abc");
		/**
		 * orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
		 * map 与 flatMap的区别： 第一个返回的仍然是optional对象
		 * 而flatMap返回的是optional的内容
		 */
		String email = Optional.ofNullable(user).map(u->u.getEmail()).orElse("efg@qq.com");
		System.out.println(email.equals(user.getEmail()));
		
		Optional.ofNullable(user).ifPresent(u->System.out.println(u.getEmail()));
		/**
		 * Optional.ofNullable(user).stream() 可以将optional转换为stream
		 * ifPresentOrElse
		  *  以上2个特性是java9提供的
		 */
		
		List<User> list = new ArrayList<>();
		list.add(user);
		list.sort((p1,p2)-> p1.getAge().compareTo(p2.getAge()));
		list.sort(Comparator.comparing(User::getAge).reversed());
		list.sort(Comparator.comparing(User::getAge).reversed());
	}
}

class User{
	private String email;
	private String name;
	private Integer age;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}