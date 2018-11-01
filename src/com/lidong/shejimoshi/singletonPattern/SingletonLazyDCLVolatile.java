package com.lidong.shejimoshi.singletonPattern;

//volatile关键字的一个作用是禁止指令重排，把instance声明为volatile之后，对它的写操作就会有一个内存屏障（什么是内存屏障？），
//这样，在它的赋值完成之前，就不用会调用读操作。
//注意：volatile 阻止的不 instance = new SingletonLazyDCLVolatile();
//这句话内部[1-2-3]的指令重排，而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）

public class SingletonLazyDCLVolatile {

	private static volatile SingletonLazyDCLVolatile instance;

	private SingletonLazyDCLVolatile() {

	}

	public static SingletonLazyDCLVolatile getInstance() {
		if (instance == null) {
			synchronized (SingletonLazyDCLVolatile.class) {
				if (instance == null) {
					instance = new SingletonLazyDCLVolatile();
				}
			}
		}
		return instance;
	}

}
