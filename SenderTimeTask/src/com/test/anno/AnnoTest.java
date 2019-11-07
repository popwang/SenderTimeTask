package com.test.anno;

import java.lang.reflect.Field;

public class AnnoTest {
	public static void main(String[] args) {
		Tom tom = new Tom();
//		tom.setAddress("abcdeF");
//		tom.setTel("010293822");
		Class cls = Tom.class;
		Person[] anno = Tom.class.getAnnotationsByType(Person.class);
		for(Person p : anno) {
			System.out.println("age:"+p.age());
			System.out.println("name:"+p.name());
			System.out.println("mail:"+p.main());
//			System.out.println("address:"+tom.getAddress());
//			System.out.println("tel:"+tom.getTel());
		}
		Field[] fields = Tom.class.getDeclaredFields();
		for(Field field : fields) {
			if(field.isAnnotationPresent(FieldValue.class)){
				FieldValue fieldValue = field.getAnnotation(FieldValue.class);
				System.out.println(field.getName()+":"+fieldValue.value());
			}
		}
	}
}
