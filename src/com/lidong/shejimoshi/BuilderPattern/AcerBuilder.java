package com.lidong.shejimoshi.BuilderPattern;

public class AcerBuilder implements Builder {

	private Product product = new ProductAcer();

	@Override
	public void buildCPU() {
		product.add("CUP: Intel 酷睿i3 2350M");
	}

	@Override
	public void buildMemory() {
		product.add("内存: 4GB DDR3 1333MHz");
	}

	@Override
	public void buildDisplayCard() {
		product.add("显卡: NVIDIA GeForce GT 520M");
	}

	@Override
	public Product getFinalResult() {
		return product;
	}

}
