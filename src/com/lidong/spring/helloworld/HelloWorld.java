package com.lidong.spring.helloworld;

public class HelloWorld {
	private String name;
	private int age;

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public void sayHello() {
		System.out.println("welcome " + name + ", age: "+age+" to spring world...");
	}
}
