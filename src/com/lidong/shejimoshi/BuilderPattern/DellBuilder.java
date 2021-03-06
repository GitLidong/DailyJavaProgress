package com.lidong.shejimoshi.BuilderPattern;

public class DellBuilder implements Builder {
	private Product product = new ProductDell();

	@Override
	public void buildCPU() {
		product.add("CUP: Intel 酷睿i7 3612QM");
	}

	@Override
	public void buildMemory() {
		product.add("内存: 8GB DDR3 1600MHz");
	}

	@Override
	public void buildDisplayCard() {
		product.add("显卡: NVIDIA GeForce GT 640M＋Intel GMA HD 4000");
	}

	@Override
	public Product getFinalResult() {
		return product;
	}
}
