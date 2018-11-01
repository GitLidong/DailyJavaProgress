package com.lidong.shejimoshi.FlyweightPattern;

//享元模式是一个非常简单的模式，它可以大大减少应用程序创建的对象，降低程序内存 的占用，增强程序的性能，
//但它同时也提高了系统复杂性，需要分离出外部状态和内部状 态，而且外部状态具有固化特性，不应该随内部状态改变而改变，否则导致系统的逻辑混 乱。

//使用场景
//系统中存在大量的相似对象。
//　　细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关，也就是说对象没 有特定身份。
//需要缓冲池的场景。

import java.util.HashMap;

public class FlyweightFactory {

	// 定义一个池容器
	private static HashMap<String, Flyweight> pool = new HashMap<String, Flyweight>();

	// 享元工厂
	public static Flyweight getFlyweight(String Extrinsic) {
		// 需要返回的对象
		Flyweight flyweight = null;
		// 在池中没有该对象
		if (pool.containsKey(Extrinsic)) {
			flyweight = pool.get(Extrinsic);
		} else {
			// 根据外部状态创建享元对象
			flyweight = new ConcreteFlyweight1(Extrinsic);
			// 放置到池中
			pool.put(Extrinsic, flyweight);
		}
		return flyweight;
	}

}
