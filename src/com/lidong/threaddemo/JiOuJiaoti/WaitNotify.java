package com.lidong.threaddemo.JiOuJiaoti;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotify {

    boolean flag = true;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newCachedThreadPool();
        WaitNotify wn = new WaitNotify();
        pool.execute(new JiTask(wn));
        pool.execute(new OuTask(wn));
        pool.shutdown();

    }

}

class JiTask implements Runnable {

    private WaitNotify wn;

    public JiTask(WaitNotify wn) {
        this.wn = wn;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i += 2) {
            try {
                synchronized (wn) {
                    while (!wn.flag) {
                        wn.wait();
                    }
                    System.out.print(i + "\t");
                    wn.flag = false;
                    wn.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Ji end");
    }
}

class OuTask implements Runnable {

    private WaitNotify wn;

    public OuTask(WaitNotify wn) {
        this.wn = wn;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i += 2) {
            try {
                synchronized (wn) {
                    while (wn.flag) {
                        wn.wait();
                    }
                    System.out.print(i + "\t");
                    wn.flag = true;
                    wn.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Ou end");
    }
}