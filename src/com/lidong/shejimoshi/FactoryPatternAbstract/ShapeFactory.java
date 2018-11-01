package com.lidong.shejimoshi.FactoryPatternAbstract;

/**
 * 步骤 6, 创建扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象。
 * 
 * @author Dong
 *
 */

public class ShapeFactory extends AbstractFactory {

	@Override
	public IColor getColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShape getShape(String shape) {
		// TODO Auto-generated method stub
		if (shape == null) {
			return null;
		} else if (shape.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if (shape.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}

}
