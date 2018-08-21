package com.lidong.threaddemo.producer_consumer;

//这里使用static obj作为锁的对象，当线程Produce启动时（假如Produce首先获得锁，则Consumer会等待），打印“A”后，会先主动释放锁，然后阻塞自己。
//Consumer获得对象锁，打印“B”，然后释放锁，阻塞自己，那么Produce又会获得锁，然后...一直循环下去，直到count = 0.这样，
//使用Synchronized和wait()以及notify()就可以达到线程同步的目的。

public class TestAAABBB {

	public static final Object obj = new Object();

	public static void main(String[] args) {

		new Thread(new ProduceAAA()).start();
		new Thread(new ConsumerBBB()).start();
	}
}

class ConsumerBBB implements Runnable {

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		int count = 10;
		while (count > 0) {
			synchronized (TestAAABBB.obj) {

				System.out.println("B");
				count--;
				TestAAABBB.obj.notify(); // 主动释放对象锁

				try {
					TestAAABBB.obj.wait();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}

class ProduceAAA implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 10;
		while (count > 0) {
			synchronized (TestAAABBB.obj) {

				// System.out.print("count = " + count);
				System.out.println("A");
				count--;
				TestAAABBB.obj.notify();

				try {
					TestAAABBB.obj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}