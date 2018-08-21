package com.lidong.spring.pratice3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test3 {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ac = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		LoginAction la = (LoginAction) ac.getBean("action3");
		// UserDaoImpl ud = ac.getBean(UserDaoImpl.class);
		la.execute();
	}

}
