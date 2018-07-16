package com.lidong.others;

public class TestConstruct {

	public static void main(String[] args) {
		Son son = new Son("sss");
		System.out.println(son.getName());
	}

}

class Father {

	private String name;

	public Father() {
		// TODO Auto-generated constructor stub
		this.name = "lee";
		System.out.println("Father Construct");
	}

	public String getName() {
		return name;
	}
}

class Son extends Father {

	private String name;

	public Son(String name) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		System.out.println("Son Construct");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
