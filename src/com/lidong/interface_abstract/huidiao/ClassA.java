package com.lidong.interface_abstract.huidiao;

public class ClassA {

    private ClassB classB;

    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public void doEvent(final String event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                classB.doWork(new ICallback() {
                    @Override
                    public void event(String result) {
                        System.out.println("程序员B告诉程序员A：" + result);
                    }
                }, event);
            }
        }).start();
    }

    public static void main(String[] args) {
        ClassA classA = new ClassA(new ClassB());
        classA.doEvent("Hello B");
    }

}
