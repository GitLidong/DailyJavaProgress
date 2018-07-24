package com.lidong.threaddemo.bingfafa;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i =0;i<5;i++) {
			pool.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		pool.shutdown();
	}

	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);

		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};

	public static void increment() {
		value.set(value.get() + 1);
	}
	
	public static int get() {
		return value.get();
	}

}

class Accessor implements Runnable {

	private int id;

	public Accessor(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
}