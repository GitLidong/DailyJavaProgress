package com.lidong.shejimoshi.FactoryPatternAbstract;

/**
 * 步骤 6, 创建扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象。
 * 
 * @author Dong
 *
 */

public class ColorFactory extends AbstractFactory {

	@Override
	public IColor getColor(String color) {
		// TODO Auto-generated method stub
		if (color == null) {
			return null;
		}
		if (color.equalsIgnoreCase("RED")) {
			return new Red();
		} else if (color.equalsIgnoreCase("GREEN")) {
			return new Green();
		}
		return null;
	}

	@Override
	public IShape getShape(String shape) {
		// TODO Auto-generated method stub
		return null;
	}

}
