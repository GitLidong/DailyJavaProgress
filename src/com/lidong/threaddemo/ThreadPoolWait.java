package com.lidong.threaddemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolWait {

    public static void main(String[] args) throws Exception {
        executorService();
    }

    private static void executorService() throws Exception{
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10) ;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,5,1, TimeUnit.MILLISECONDS,queue) ;
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("running2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        poolExecutor.shutdown();
        while (!poolExecutor.awaitTermination(1, TimeUnit.SECONDS)){
            System.out.println("线程还在执行。。。");
        }
        System.out.println("main over");
    }

}
