package com.lidong.spring.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorld {

	private static ApplicationContext context;
	private static HelloWorld hello;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2. 从IOC容器中获取Bean实例
		hello = (HelloWorld)context.getBean("helloworld");
		// 3.调用sayHello方法
		hello.sayHello();
		
		HelloWorld bbb = (HelloWorld) context.getBean("helloworld");
		bbb.sayHello();
	}

}
