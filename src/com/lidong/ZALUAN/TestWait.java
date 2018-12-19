package com.lidong.ZALUAN;

public class TestWait {

    public static boolean flag = true;

    public static void main(String[] args) {
        TestWait demo = new TestWait();


        new Thread(() -> {
            try {
                synchronized (demo) {
                    while (demo.flag) {
                        demo.wait();
                    }
                    System.out.println("111 execute ");
                    demo.flag = true;
                    demo.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronized (demo) {
                    while (!demo.flag) {
                        demo.wait();
                    }
                    System.out.println("2222  execute ");
                    demo.flag = false;
                    demo.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("end !!!!");
    }
}
