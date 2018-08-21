package com.lidong.shejimoshi.BuilderPattern;

public class ConcreteBuilder extends Builder {

	private Product product = new Product();

	@Override
	public void buildPartOne() {
		product.setPartOne("灵魂");
		System.out.println("灵魂造好");
	}

	@Override
	public void buildPartTwo() {
		product.setPartTwo("感官");
		System.out.println("感官造好");
	}

	@Override
	public void buildPartThree() {
		product.setPartThree("耳目");
		System.out.println("耳目造好");
	}

	@Override
	public Product retrieveResult() {
		// TODO Auto-generated method stub
		return product;
	}

}
