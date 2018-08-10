package com.lidong.threaddemo.synch_lock;

public class Demo1Sync {
	boolean flag = false;

	public synchronized void set() {
		// 用while而不用if的原因，这样每个线程在wait等待醒来后都必须再次判断flag
		while (flag) {
			try {
				this.wait();
			} catch (Exception e) {
			}
			System.out.println("生产者");
			flag = true;
			this.notifyAll();// 这将唤醒所有线程(本方线程和对方线程)，消耗资源
		}
	}

	public synchronized void out() {
		while (!flag) {
			try {
				this.wait();
			} catch (Exception e) {
			}
			System.out.println("消费者");
			flag = false;
			// 这将唤醒所有线程(本方线程和对方线程)，消耗资源
			this.notifyAll();
		}
	}
}
