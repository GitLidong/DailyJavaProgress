package com.lidong.threaddemo.bingfafa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrnamentalGarden {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			pool.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		pool.shutdown();
		
		System.out.println("Total: "+Entrance.getTotal());
		System.out.println("Sum of Entrances: "+Entrance.sumEntrance());
	}

}

class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) {// 有一半时间会让步
			Thread.yield();
		}
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

class Entrance implements Runnable {

	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();

	private int number = 0;// 内部的计数，记录该entrance的人数
	private final int id;// 该Entrance的标示

	private static volatile boolean canceled = false;

	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());

			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Stopping " + this);
	}

	public synchronized int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Entrance " + id + ": " + getNumber();
	}

	public static int getTotal() {
		return count.value();
	}

	public static int sumEntrance() {
		int sum = 0;
		for (Entrance temp : entrances) {
			sum += temp.getNumber();
		}
		return sum;
	}

}