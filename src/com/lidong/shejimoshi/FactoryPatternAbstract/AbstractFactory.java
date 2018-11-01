package com.lidong.shejimoshi.FactoryPatternAbstract;

/**
 * 步骤 5,为 Color 和 Shape 对象创建抽象类来获取工厂。
 * 
 * @author Dong
 *
 */

public abstract class AbstractFactory {
	public abstract IColor getColor(String color);

	public abstract IShape getShape(String shape);
}
