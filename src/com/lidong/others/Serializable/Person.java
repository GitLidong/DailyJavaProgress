package com.lidong.others.Serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {

	// SerialVersionUID属性
	// 当我们创建A类的对象a并进行序列化传输时，如果此时我们修改了A类，增加了某些新的属性，
	// 这时候如果不对其进行判断而进行反序列化的话，将会导致运行时异常，两者类型不匹配。
	// 因此，这里使用SerialVersionUTD属性，该属性用来唯一标识一个类的版本
	// 显式声明SerialVersionUID;
	private static final long serialVersionUID = 55799L;

	private String name;
	private Integer age;
	private Salay salay;

	// 编写构造器
	public Person(String name, Integer age, Salay salay) {
		this.name = name;
		this.age = age;
		this.salay = salay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Salay getSalay() {
		return salay;
	}

	public void setSalay(Salay salay) {
		this.salay = salay;
	}

	// 编写序列化控制方法
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		// 控制序列化的参数
		out.writeDouble(salay.getBase());
	}

	// 编写反序列化控制方法
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		// 控制反序列化的参数
		salay = new Salay(in.readDouble(), 0.0);
	}
}
