package com.lidong.threaddemo.thread_cooperation;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		FoodQueue food1Queue = new FoodQueue(), food2Queue = new FoodQueue(), food3Queue = new FoodQueue();
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(new makeStep1Food(food1Queue));
		pool.execute(new makeStep2Food(food1Queue, food2Queue));
		pool.execute(new makeStep3Food(food2Queue, food3Queue));
		pool.execute(new EatStep3Food(food3Queue));
		TimeUnit.SECONDS.sleep(5);
		pool.shutdownNow();
	}

}

class Food {
	public enum Status {
		STEP1, STEP2, STEP3
	}

	private Status foodStatus = Status.STEP1;

	final int id;

	public Food(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public void goStep2() {
		foodStatus = Status.STEP2;
	}

	public void goStep3() {
		foodStatus = Status.STEP3;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Food " + id + " : " + foodStatus;
	}
}

class FoodQueue extends LinkedBlockingDeque<Food> {

}

class makeStep1Food implements Runnable {

	private FoodQueue food1Queue;
	private int count = 0;
	private Random random = new Random(47);

	public makeStep1Food(FoodQueue foodQueue) {
		// TODO Auto-generated constructor stub
		this.food1Queue = foodQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!Thread.currentThread().isInterrupted()) {
				TimeUnit.MICROSECONDS.sleep(100 + random.nextInt(500));
				Food food = new Food(count++);
				System.out.println(food);
				food1Queue.put(food);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("makeStep1Food end...........");
	}
}

class makeStep2Food implements Runnable {
	private FoodQueue food1Queue;
	private FoodQueue food2Queue;

	public makeStep2Food(FoodQueue food1Queue, FoodQueue food2Queue) {
		// TODO Auto-generated constructor stub
		this.food1Queue = food1Queue;
		this.food2Queue = food2Queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while (!Thread.currentThread().isInterrupted()) {
				Food food = food1Queue.take();
				food.goStep2();
				System.out.println(food);
				food2Queue.put(food);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("makeStep2Food end...........");
	}
}

class makeStep3Food implements Runnable {
	private FoodQueue food2Queue;
	private FoodQueue food3Queue;

	public makeStep3Food(FoodQueue food2Queue, FoodQueue food3Queue) {
		// TODO Auto-generated constructor stub
		this.food2Queue = food2Queue;
		this.food3Queue = food3Queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Food food = food2Queue.take();
				food.goStep3();
				System.out.println(food);
				food3Queue.put(food);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("makeStep3Food end...........");
	}
}

class EatStep3Food implements Runnable {
	private FoodQueue food3Queue;

	public EatStep3Food(FoodQueue food3Queue) {
		// TODO Auto-generated constructor stub
		this.food3Queue = food3Queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Food food = food3Queue.take();
				System.out.println("eat :" + food);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("EatStep3Food end...........");
	}
}