package com.lidong.threaddemo.deadlock;

public class DeadLock {
    public static void main(String[] args) {
        DeadLockTask t1 = new DeadLockTask();
        t1.setName("线程1");
        DeadLockTask t2 = new DeadLockTask();
        t2.setName("线程2");
        t1.flag = true;
        t2.flag = false;
        t1.start();
        t2.start();
    }
}

class DeadLockTask extends Thread {

    public boolean flag = true;
    static Object o1 = new Object();// 定义两个公共资源
    static Object o2 = new Object();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (flag) {
            System.out.println(Thread.currentThread().getName() + "等待o1");
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "占用o1,等待o2");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "占用o2");
                }
                System.out.println(Thread.currentThread().getName() + "释放o2");
            }
            System.out.println(Thread.currentThread().getName() + "释放o1");
        } else {
            System.out.println(Thread.currentThread().getName() + "等待o2");
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "占用o2,等待o1");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "占用o1");
                }
                System.out.println(Thread.currentThread().getName() + "释放o1");
            }
            System.out.println(Thread.currentThread().getName() + "释放o2");
        }
    }
}

