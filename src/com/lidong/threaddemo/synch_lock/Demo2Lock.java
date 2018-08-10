package com.lidong.threaddemo.synch_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2Lock {
	boolean flag = false;
	private Lock lock = new ReentrantLock();

	private Condition condition_pro = lock.newCondition();// 生产者对象

	private Condition condition_con = lock.newCondition();// 消费者对象

	public void set(String name) throws Exception {
		lock.lock();// 加锁
		try {
			while (flag) {
				condition_pro.await();
				System.out.println("生产者");
				flag = true;
				condition_con.signalAll();// 指定唤醒消费方
			}
		} finally {
			lock.unlock();// 解锁
		}
	}

	public void out() throws Exception {
		lock.lock();
		try {
			while (!flag) {
				condition_con.await();
				System.out.println("消费者");
				flag = false;
				condition_pro.signal();// 指定唤醒生产方
			}
		} finally {
			lock.unlock();
		}
	}
}
