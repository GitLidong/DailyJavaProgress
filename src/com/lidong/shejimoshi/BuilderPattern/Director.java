package com.lidong.shejimoshi.BuilderPattern;

public class Director {
	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	public void construct() {
		builder.buildPartOne();
		builder.buildPartTwo();
		builder.buildPartThree();
	}
}
