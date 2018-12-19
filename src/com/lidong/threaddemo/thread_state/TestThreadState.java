package com.lidong.threaddemo.thread_state;

public class TestThreadState {

    public static void main(String[] args) {
//        Thread thread = new Thread();
//        System.out.println(thread.getState()); // NEW

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    System.out.println(i);
                }
            }
        }, "RUNNABLE-Thread");
        thread.start();
    }
}
