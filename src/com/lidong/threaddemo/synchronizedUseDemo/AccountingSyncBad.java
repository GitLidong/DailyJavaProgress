package com.lidong.threaddemo.synchronizedUseDemo;

public class AccountingSyncBad implements Runnable{
    static int i=0;
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2=new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
    
    /**
     * 上述代码犯了严重的错误，虽然我们使用synchronized修饰了increase方法，
     * 但却new了两个不同的实例对象，这也就意味着存在着两个不同的实例对象锁，
     * 因此t1和t2都会进入各自的对象锁，也就是说t1和t2线程使用的是不同的锁，因此线程安全是无法保证的。
     */
}