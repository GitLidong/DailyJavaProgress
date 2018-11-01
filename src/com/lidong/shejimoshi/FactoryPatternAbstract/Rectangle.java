package com.lidong.shejimoshi.FactoryPatternAbstract;

/**
 * 步骤 2,创建实现接口的实体类。
 * 
 * @author Dong
 *
 */

public class Rectangle implements IShape {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Inside Rectangle::draw() method.");
	}

}
