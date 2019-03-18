package com.lidong.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(4);

        pool.execute(new MyTask("Task1 ++: ", true));
        pool.execute(new MyTask("Task2: ++", true));
        pool.execute(new MyTask("Task3: --", false));
        pool.execute(new MyTask("Task4: --x", false));

    }

}

class MyTask implements Runnable {

    private static volatile int data = 0;

    private boolean flag;
    private String name;

    MyTask(String name, boolean flag) {
        this.name = name;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            synchronized ("sync") {
                if (flag) data++;
                else data--;
                System.out.println(name + data);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
