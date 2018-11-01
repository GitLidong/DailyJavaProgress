package com.lidong.shejimoshi.PrototypePattern;

//（1）根据客户端要求实现动态创建对象，客户端不需要知道对象的创建细节，便于代码的维护和扩展。

//（2）使用原型模式创建对象比直接new一个对象在性能上要好的多，因为Object类的clone方法是一个本地方法，
//	它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。所以在需要重复地创建相似对象时可以考虑使用原型模式。
//	比如需要在一个循环体内创建对象，假如对象创建过程比较复杂或者循环次数很多的话，
//	使用原型模式不但可以简化创建过程，而且可以使系统的整体性能提高很多。
//（3） 原型模式类似于工厂模式，但它没有了工厂模式中的抽象工厂和具体工厂的层级关系，代码结构更清晰和简单。

public class TestDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		Prototype prototype = new ConcretePrototype1();
		ConcretePrototype1 prototype1 = (ConcretePrototype1) prototype.clone();
		System.out.println(prototype1.classFlag);
	}
}
