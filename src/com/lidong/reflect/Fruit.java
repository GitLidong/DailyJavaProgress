package com.lidong.reflect;

public class Fruit {

    public int num;
    private String type;

    public Fruit() {
        System.out.println("无参构造器 Run...........");
    }

    public Fruit(String type) {
        System.out.println("有参构造器 Run..........." + type);
    }

    public void show() {
        System.out.println("Fruit type = " + type);
    }

    public void show(int num) {
        System.out.println("Fruit type = " + type + ".....Fruit num = " + num);
    }

}
