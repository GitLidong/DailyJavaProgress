package com.lidong.proxy;

/**
 * 静态代理
 */

public class ProxyTest implements Person {

    private Person o;

    public ProxyTest(Person o) {
        this.o = o;
    }

    @Override
    public void sayHello(String content, int age) {
        System.out.println("ProxyTest sayHello begin");
        //在代理类的方法中 间接访问被代理对象的方法
        o.sayHello(content, age);
        System.out.println("ProxyTest sayHello end");
    }

    @Override
    public void sayGoodBye(boolean seeAgin, double time) {
        System.out.println("ProxyTest sayHello begin");
        //在代理类的方法中 间接访问被代理对象的方法
        o.sayGoodBye(seeAgin, time);
        System.out.println("ProxyTest sayHello end");
    }

    public static void main(String[] args) {
        Student student = new Student();
        ProxyTest proxy = new ProxyTest(student);
        proxy.sayHello("welcome to java", 20);
        proxy.sayGoodBye(true, 100);
    }

}
