package com.lidong.threaddemo;

public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReadyThread implements Runnable {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new Thread(new ReadyThread()).start();
        number = 42;
        ready = true;
    }
}
