package com.lidong.reflect.factory;

public class ReflectTest {

    public static void main(String[] args) {
        Fruit fruit = BasicFactory.getFactory().newInstance(Fruit.class);
        System.out.println(fruit);
    }

}
