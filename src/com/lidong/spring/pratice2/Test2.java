package com.lidong.spring.pratice2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//a  setter
//原理 : 在目标对象中，定义需要注入的依赖对象对应的属性和setter方法；
//“让ioc容器调用该setter方法”，将ioc容器实例化的依赖对象通过setter注入给目标对象，封装在目标对象的属性中。
//
//
//b  构造器
//原理 : 为目标对象提供一个构造方法，在构造方法中添加一个依赖对象对应的参数。
//ioc容器解析时，实例化目标对象时会自动调用构造方法，ioc只需要为构造器中的参数进行赋值；
//将ioc实例化的依赖对象作为构造器的参数传入。
//
//
//【接口注入
//原理 : 为依赖对象提供一个接口实现，将接口注入给目标对象，实现将接口的实现类注入的效果。比如HttpServletRequest  HttpServletResponse接口
//注意：这是ioc提供的方式，spring中的ioc技术并没有实现该种注入方式】
//
//
//c  方法注入
//原理 : 在目标对象中定义一个普通的方法，将方法的返回值设置为需要注入的依赖对象类型。
//通过ioc容器调用该方法，将其创建的依赖对象作为方法的返回值返回给目标对象。   

public class Test2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginAction demo = (LoginAction) factory.getBean("action");
		System.out.println(demo.execute());
	}

}
