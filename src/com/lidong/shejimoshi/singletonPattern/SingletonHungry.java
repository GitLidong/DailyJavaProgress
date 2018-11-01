package com.lidong.shejimoshi.singletonPattern;

//优点：没有加锁，执行效率会提高。
//缺点：类加载时就初始化，浪费内存。

public class SingletonHungry {

	private static SingletonHungry instance = new SingletonHungry();

	private SingletonHungry() {
		// TODO Auto-generated constructor stub
	}

	public static SingletonHungry getInstance() {
		return instance;
	}

}
