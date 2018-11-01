package com.lidong.shejimoshi.PrototypePattern;

// 2, 具体原型文件

public class ConcretePrototype1 extends Prototype {

	public static int classFlag = 1;

	// 浅克隆
	// @Override
	// public Object clone() throws CloneNotSupportedException {
	// return (ConcretePrototype1) super.clone();
	// }

	
	//深克隆
	@Override
	public Object clone() {
		// 最简单的克隆，新建一个自身对象，由于没有属性就不再复制值了
		Prototype prototype = new ConcretePrototype1();
		return prototype;
	}
	
//	至于浅克隆和深克隆的区别是什么呢，简单的说：如果一个对象中只有基本类型属性，
//	那深克隆和浅克隆效果都是一样的，基本类型数据不管是用深克隆还是浅克隆都会被克隆出一份，
//	但如果对象中包含引用对象属性，那浅克隆其实这是拷贝了一份引用，而深克隆确实把整个引用对象都拷贝了一份。

}
