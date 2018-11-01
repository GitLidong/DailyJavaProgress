package com.lidong.shejimoshi.FactoryPatternAbstract;

public class TestDemo {

	public static void main(String[] args) {
		//获取形状工厂
	      AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
	 
	      //获取形状为 Circle 的对象
	      IShape shape1 = shapeFactory.getShape("CIRCLE");
	 
	      //调用 Circle 的 draw 方法
	      shape1.draw();
	 
	      //获取形状为 Rectangle 的对象
	      IShape shape2 = shapeFactory.getShape("RECTANGLE");
	 
	      //调用 Rectangle 的 draw 方法
	      shape2.draw();
	      
	      //获取形状为 Square 的对象
	      IShape shape3 = shapeFactory.getShape("SQUARE");
	 
	      //调用 Square 的 draw 方法
	      shape3.draw();
	 
	      //获取颜色工厂
	      AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
	 
	      //获取颜色为 Red 的对象
	      IColor color1 = colorFactory.getColor("RED");
	 
	      //调用 Red 的 fill 方法
	      color1.fill();
	 
	      //获取颜色为 Green 的对象
	      IColor color2 = colorFactory.getColor("Green");
	 
	      //调用 Green 的 fill 方法
	      color2.fill();
	 
	      //获取颜色为 Blue 的对象
	      IColor color3 = colorFactory.getColor("BLUE");
	 
	      //调用 Blue 的 fill 方法
	      color3.fill();
	}
	
}
