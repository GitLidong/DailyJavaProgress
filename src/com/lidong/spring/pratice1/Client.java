package com.lidong.spring.pratice1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		/*
		 * 传统的通过new对象建立类之间的关系 */
		//UserManager userManager = new UserManagerImpl(new UserDao4OracleImpl()); 
		//UserManager userManager = new UserManagerImpl(new UserDao4MySqlImpl());
		//userManager.save("张三", "123");
		
		/**
		 * IOC思想 通过工厂类解析xml文件，以“反射”的方式创建对象：
		 */
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserManager userManager = (UserManager) factory.getBean("userManager");
		userManager.save("张三", "123");
		
		/**
		 * IOC思想 实际的执行过程，这也是为什么需要setter方法或构造方法的原因：
		 */
		// UserManagerImpl userManager = new UserManagerImpl();
		// userManager.setUserDao(new UserDao4MySqlImpl());
		// userManager.save("张三", "123");
	}

}
