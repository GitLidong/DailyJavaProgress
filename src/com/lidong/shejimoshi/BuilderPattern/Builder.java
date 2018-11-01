package com.lidong.shejimoshi.BuilderPattern;

public interface Builder {
	public void buildCPU(); // CUP

	public void buildMemory(); // 内存

	public void buildDisplayCard(); // 显卡

	public Product getFinalResult();// 最终产品
}
