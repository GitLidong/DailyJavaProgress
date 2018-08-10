package com.lidong.threaddemo.deadlock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PhilosopherChopstickDemo {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int ponder = 5;
		int size = 5;
		Chopstick[] chopsticks = new Chopstick[size];
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for (int i = 0; i < size; i++) {
			chopsticks[i] = new Chopstick();
		}

		for (int i = 0; i < size; i++) {
			pool.execute(new Philosopher(chopsticks[i], chopsticks[(i+1)%size], i, ponder));
		}
		TimeUnit.SECONDS.sleep(1);
		pool.shutdownNow();
	}

}

class Chopstick {
	private boolean taken = false;

	public synchronized void take() throws InterruptedException {
		while (taken) {
			wait();
		}
	}

	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}

class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;

	private final int id;
	private final int ponderFactor;
	private Random random = new Random(47);

	private void pause() throws InterruptedException {
		if (ponderFactor == 0) {
			return;
		}
		TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
	}

	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		// TODO Auto-generated constructor stub
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!Thread.interrupted()) {
				System.out.println(this + " " + "thinking.");
				pause();
				System.out.println(this + " " + "grabhbing right.");
				right.take();
				System.out.println(this + " " + "grabhbing left.");
				left.take();
				System.out.println(this + " " + "eatting.");
				pause();
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Philosopher " + id;
	}
}
