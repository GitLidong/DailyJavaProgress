package com.lidong.shejimoshi.BuilderPattern;

import java.util.ArrayList;
import java.util.List;

public class Product {
	protected List<String> parts = new ArrayList<>();

	// 添加部件
	public void add(String part) {
		parts.add(part);
	}

	// 显示产品信息
	public void show() {
		System.out.print("产品部件信息：");
		for (String part : parts) {
			System.out.print(part + "\t");
		}
	}
}
