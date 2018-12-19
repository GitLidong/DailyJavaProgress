package com.lidongA.spring.spring_aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LOLDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
		Battle battle = (Battle) context.getBean("Battle");
		battle.tuan2();
	}

}
