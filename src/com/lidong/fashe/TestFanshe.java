package com.lidong.fashe;

import java.lang.reflect.Constructor;

public class TestFanshe {

	public static void main(String[] args) throws ClassNotFoundException {
		// 1.加载Class对象
		Class clazz = Class.forName("com.lidong.fashe.Student");

		// 2.获取所有公有构造方法
		System.out.println("**********************所有公有构造方法*********************************");
		Constructor[] conArray = clazz.getConstructors();
		for (Constructor c : conArray) {
			System.out.println(c);
		}

		System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
		conArray = clazz.getDeclaredConstructors();
		for (Constructor c : conArray) {
			System.out.println(c);
		}

	}

	private void aaa() {
		// 第一种方式获取Class对象
		Student stu1 = new Student();// 这一new 产生一个Student对象，一个Class对象。
		Class stuClass = stu1.getClass();// 获取Class对象
		System.out.println(stuClass.getName());

		// 第二种方式获取Class对象
		Class stuClass2 = Student.class;
		System.out.println(stuClass == stuClass2);// 判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

		// 第三种方式获取Class对象
		try {
			Class stuClass3 = Class.forName("fanshe.Student");// 注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
			System.out.println(stuClass3 == stuClass2);// 判断三种方式是否获取的是同一个Class对象
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
