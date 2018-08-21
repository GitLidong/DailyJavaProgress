package com.lidong.spring.pratice4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test4 Main Func");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ac = new FileSystemXmlApplicationContext("src/applicationContext.xml");

		CityDaoImpl cityDaoImpl = (CityDaoImpl) ac.getBean("dao4");

		cityDaoImpl.dropTableIfExist();
		
		if (!cityDaoImpl.createCityTable()) {
			System.out.println("create table fail! table already exist or othes reason");
		}
		System.out.println("create table city succeed!");
	    cityDaoImpl.addCity(new City(1, 1, "city1"));
	    cityDaoImpl.addCity(new City(2, 2, "city2"));
	    cityDaoImpl.addCity(new City(3, 3, "city3"));
	    cityDaoImpl.addCity(new City(4, 4, "city4"));
	    
	    cityDaoImpl.selectAllCitys().forEach(city -> System.out.println(city));
	    
	    System.out.println("Other ssssssssssssssssssss");
	    
	    cityDaoImpl.selectAllCitys().forEach(System.out::println);
	    
	    
	}

}
