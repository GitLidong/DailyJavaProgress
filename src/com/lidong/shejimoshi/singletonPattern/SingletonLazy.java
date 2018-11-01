package com.lidong.shejimoshi.singletonPattern;

//这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式

public class SingletonLazy {

	private static SingletonLazy instance;

	private SingletonLazy() {

	}

	public static SingletonLazy getInstance() {
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}

}
