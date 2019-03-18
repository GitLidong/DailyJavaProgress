package com.lidong.interface_abstract.huidiao;

public class ClassB {

    public void doWork(ICallback callback, String event) {
        System.out.println("程序员A告诉程序员B需要干的事：" + event);
        System.out.println("程序员B：干活.....");
        String result = "完成工作";
        callback.event(result);
    }
}
