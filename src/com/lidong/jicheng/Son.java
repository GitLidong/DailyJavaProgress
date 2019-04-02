package com.lidong.jicheng;

public class Son extends Parent {

    public static String name2 = "Son name2";

    //@Override //会报错
    public static void info2() {
        System.out.println("Son info2");
    }

    @Override
    public void info3() {
        super.info3();
        System.out.println("Son info3");
    }

    public static void main(String[] args) {
        System.out.println(name);//父类的静态属性可以被继承
        System.out.println(Parent.name2);
        System.out.println(name2);//父类的静态属性不是被重写了，而是被隐藏了
        info();//父类的静态方法可以被继承
        Parent.info2();
        info2();//父类的静态方法不可以被覆盖，不可以被重写，而只是因为同名被隐藏了

        Son son = new Son();
        son.info3();
    }

}
