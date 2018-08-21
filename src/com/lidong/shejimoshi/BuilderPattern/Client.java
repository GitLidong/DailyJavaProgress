package com.lidong.shejimoshi.BuilderPattern;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Builder builder = new ConcreteBuilder();
		Director diretor = new Director(builder);
		diretor.construct();
		System.out.println(builder.retrieveResult());
	}

}
