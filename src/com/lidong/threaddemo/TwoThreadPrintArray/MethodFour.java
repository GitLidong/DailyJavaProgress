package com.lidong.threaddemo.TwoThreadPrintArray;

import java.util.concurrent.atomic.AtomicInteger;

public class MethodFour implements Tasks {

    private AtomicInteger threadToGo = new AtomicInteger(1);

    @Override
    public Runnable newThreadOne() {
        // TODO Auto-generated method stub
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            String[] arr = inputArr;

            @Override
            public void run() {
                for (int i = 0; i < arr.length; i += 2) {
                    while (threadToGo.get() == 2) {

                    }
                    Helper.print(arr[i], arr[i + 1]);
                    threadToGo.set(2);
                }
            }
        };
    }

    @Override
    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    while (threadToGo.get() == 1) {
                    }
                    Helper.print(arr[i]);
                    threadToGo.set(1);
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodFour four = new MethodFour();
        Helper.instance.run(four.newThreadOne());
        Helper.instance.run(four.newThreadTwo());
        Helper.instance.shutdown();
    }

}
