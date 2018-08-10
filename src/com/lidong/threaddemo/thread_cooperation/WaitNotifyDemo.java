package com.lidong.threaddemo.thread_cooperation;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.lidong.set_demo.lidong.LinkedListQueue;

/**
 * 
 * @author LiDong
 * 
 *         wait()和notify()、notifyAll()方法这些方法均属于基类Object
 */

public class WaitNotifyDemo {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LinkedListQueue<Integer> integerQueue = new LinkedListQueue();
		Producer producer = new Producer(integerQueue);
		Consumer consumer = new Consumer(integerQueue);
		ExecutorService pool = Executors
				.newCachedThreadPool(new CaughtExceptionThreadFactory());
		pool.execute(producer);
		pool.execute(consumer);
		TimeUnit.SECONDS.sleep(2);
		pool.shutdownNow();
	}
}

/****************************************** start *********************************************************************/
class MyUncaughtExeception implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println(t.getName() + " caught: " + e);
	}
}

class CaughtExceptionThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExeception());// 设定线程工厂的异常处理器
		return t;
	}
}

/******************************************** end ***********************************************************************/

class Producer implements Runnable {

	LinkedListQueue<Integer> data;
	Random random;

	public Producer(LinkedListQueue<Integer> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		random = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (data) {
			while (!Thread.currentThread().isInterrupted()) {
				int ele = random.nextInt(10) + 1;
				System.out.println(Thread.currentThread().getName() + " add :"
						+ ele + " , now size: " + data.size());
				data.add(ele);
			}
		}
	}
}

class Consumer implements Runnable {

	LinkedListQueue<Integer> data;
	Random random;

	public Consumer(LinkedListQueue<Integer> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		random = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			int ele = data.remove();
			System.out.println(Thread.currentThread().getName() + " consume :"
					+ ele + " , now size: " + data.size());
		}
	}
}