package com.lidong.interface_abstract;

public class AADomin extends AA implements IA {

    //非抽象方法既可以直接继承，也可以覆盖
    @Override
    public void AAInfo2() {
        super.AAInfo2();
    }


    //抽象方法，需要实现
    //也可以通过再次声明其方法为抽象的方式，留给其子类来实现，但此类必须也声明为抽象类
    @Override
    public void AAInfo() {
        System.out.println(Ainfo + " , " + othres.othres);
    }


    @Override
    public void IAInof() {
        System.out.println(Iinfo + " , " + Iothers.othres);
    }


    public static void main(String[] args) {
        AADomin domin = new AADomin();
        domin.AAInfo();
        domin.AAInfo2();
        domin.IAInof();
    }

}
