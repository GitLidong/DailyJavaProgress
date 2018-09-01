package com.ldddd.spring.spring1.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1 加载spring配置文件，根据创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2 得到配置创建的对象
		Bean1 bean1 = (Bean1) context.getBean("bean1");
		System.out.println(bean1);
	}

}
