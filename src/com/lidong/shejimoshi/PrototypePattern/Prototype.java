package com.lidong.shejimoshi.PrototypePattern;

// 1 ,抽象原型文件

public class Prototype implements Cloneable {

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
