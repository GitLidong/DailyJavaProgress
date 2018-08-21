package com.lidong.shejimoshi.BuilderPattern;

public abstract class Builder {
	public abstract void buildPartOne();

	public abstract void buildPartTwo();

	public abstract void buildPartThree();

	public abstract Product retrieveResult();
}
