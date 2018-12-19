package com.lidong.threaddemo.sync_ld;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(10);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(new WorkRunnable(count, i));
        }

        count.await();
        System.out.println("All work done");
    }

}

class WorkRunnable implements Runnable {

    private CountDownLatch countDownLatch;
    private int i;

    public WorkRunnable(CountDownLatch countDownLatch, int i) {
        this.countDownLatch = countDownLatch;
        this.i = i;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("WorkRunnable i : " + i);
        countDownLatch.countDown();
    }
}
