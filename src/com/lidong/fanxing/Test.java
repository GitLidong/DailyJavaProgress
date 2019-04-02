package com.lidong.fanxing;

public class Test {

    public static void main(String[] args) {
        //上界<? extends T>不能往里存，只能往外取
        Plate<? extends Fruit> plate = new Plate<Apple>(new Apple());
        plate.get().info();
        //plate.set(new Apple());  //不能存，因为盘子里面存放的都是Fruit的子类，

        //下界<? super T>不影响往里存，但往外取只能放在Object对象里
        Plate<? super Fruit> plate1 = new Plate<Fruit>(new Fruit());
        //Apple apple = plate1.set(new Apple()); //不能读，读出来的都是Object，因为盘子里面存放的是Fruit的超类，不能向下转型
        Object object = plate1.get();
        plate1.get().info();

    }

}
