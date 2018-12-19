package com.lidong.threaddemo.close_thread;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("resource/log.log");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        LogWriter logWriter = new LogWriter(pw);
        logWriter.start();

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            pool.execute(new TestTheread(logWriter));
        }

        try {
            TimeUnit.SECONDS.sleep(5);
            pool.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String end = "end";
        try {
            logWriter.log(end);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(end);

    }

    static class TestTheread implements Runnable {

        private LogWriter logWriter;
        private int i;

        public TestTheread(LogWriter logWriter) {
            this.logWriter = logWriter;
            this.i = 0;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    logWriter.log(Thread.currentThread().getName() + (i++));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}