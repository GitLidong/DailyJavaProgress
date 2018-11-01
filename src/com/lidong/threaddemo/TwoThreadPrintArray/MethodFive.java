package com.lidong.threaddemo.TwoThreadPrintArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MethodFive implements Tasks {

    private CyclicBarrier barrier;
    private List<String> list;

    public MethodFive() {
        list = Collections.synchronizedList(new ArrayList<>());
        barrier = new CyclicBarrier(2, newBarrierAction());
    }

    @Override
    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            @Override
            public void run() {
                for (int i = 0; i < arr.length; i += 2) {
                    try {
                        list.add(arr[i]);
                        list.add(arr[i + 1]);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {

            private String[] arr = inputArr;

            @Override
            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    try {
                        list.add(arr[i]);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private Runnable newBarrierAction() {
        return () -> {
            Collections.sort(list);
            list.forEach(c -> System.out.print(c));
            list.clear();
        };
    }

    public static void main(String[] args) {
        MethodFive methodFive = new MethodFive();
        Helper.instance.run(methodFive.newThreadOne());
        Helper.instance.run(methodFive.newThreadTwo());
        Helper.instance.shutdown();
    }
}
