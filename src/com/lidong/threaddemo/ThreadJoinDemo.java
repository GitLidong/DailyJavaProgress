package com.lidong.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadJoinDemo {
    public static void main(String[] args) {
//        thread3.start();
//        thread2.start();
//        thread1.start();

    	ExecutorService exec = Executors.newSingleThreadExecutor();
    	exec.execute(thread1);
    	exec.execute(thread2);
    	exec.execute(thread3);

    }

    static Thread thread1 = new Thread(new Runnable() {

        @Override
        public void run() {
            System.out.println("t1");
        }
    });
    static Thread thread2 = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        }
    });
    static Thread thread3 = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        }
    });
}
